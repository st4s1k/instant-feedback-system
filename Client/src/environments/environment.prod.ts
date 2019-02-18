export const environment = {
  production: true,

  jsonServerUrl: 'http://localhost:3000',
  serverUrl: 'http://localhost:9000',

  presentationsApiRoute: '/api/presentations',
  messagesApiRoute: '/api/messages',
  participantsApiRoute: '/api/participants',
  marksApiRoute: '/api/marks',
  usersApiRoute: '/api/users',
  signInApiRoute: '/api/auth',
  signUpApiRoute: '/api/auth',

  userRole: 'USER',
  adminRole: 'ADMIN',

  msgTypeFeedback: 'TYPE_FEEDBACK',
  msgTypeQuestion: 'TYPE_QUESTION',

  defaultPageSize: 3,
  defaultUserPageSize: 4
};
