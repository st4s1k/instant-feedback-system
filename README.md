### Required software

1. [NPM Package Manager](https://www.npmjs.com/get-npm)
2. [Visual Studio Code](https://code.visualstudio.com/)
3. [Git](https://git-scm.com/download) 

### Setup working environment

1. Clone this repository

2. Open cmd as admininstrator (or `sudo -i` on linux)

3. Go into the `Client` folder, inside the cloned repository

4. Run: `npm install -g @angular/cli`


### Usage

1. Set server IP address:
	1.1. open with a text editor:
	`Client/src/environments/environment.prod.ts`
	and
	`Client/src/environments/environment.ts` 
	1.2. Change the `apiUrl: 'http://IP_ADDRESS:PORT'` to match your server IP
2. Start server (from `Client` folder): `npm start`
3. Stop server: `Ctrl+C`

[!] All the changes made on client are updated in real time, so you don't need to restart the client, just save the edited document and the client web-page will reload automatically.
[!] If something doesn't work, check cmd/terminal, where `npm start` is running, if there are any errors.