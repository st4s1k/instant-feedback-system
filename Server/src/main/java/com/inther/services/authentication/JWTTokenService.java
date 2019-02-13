package com.inther.services.authentication;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class JWTTokenService implements TokenService {
    private static final String DOT = ".";
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    String issuer;
    String secretKey;

    JWTTokenService(@Value("${jwt.issuer:inther}") final String issuer,
                    @Value("${jwt.secret:secret}") final String secret) {
        super();
        this.issuer = requireNonNull(issuer);
        this.secretKey = BASE64.encode(requireNonNull(secret));
    }

    @Override
    public String generate(final Map<String, String> attributes) {
        return newToken(attributes);
    }

    private String newToken(final Map<String, String> attributes) {
        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer);

        claims.putAll(attributes);

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public Map<String, String> verify(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    @Override
    public Map<String, String> untrusted(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer);

        final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
    }

    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (final IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }
}