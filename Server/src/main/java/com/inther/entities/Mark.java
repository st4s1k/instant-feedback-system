package com.inther.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marks")
public class Mark
{
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Presentation presentation;

    @ManyToOne
    private User user;

    private Integer value;

    public Mark setPresentation(Presentation presentation) {
        this.presentation = presentation;
        return this;
    }

    public Mark setUser(User user) {
        this.user = user;
        return this;
    }

    public Mark setValue(Integer value) {
        this.value = value;
        return this;
    }

    public Mark updateBy(Mark mark) {
        this.presentation = mark.presentation;
        this.user = mark.user;
        this.value = mark.value;
        return this;
    }
}