Random quote generator (Kostur).

Generator. (server - server socket)
Client. (socket)
File Reader. (server)
File struktura (linija, quote) (server) (linija, quote)
Spasavati quote-ove u fajl koji ce imati format: [TIMESTAMP]quote.
Server ima final pass. Client salje serveru pass if(pass true) vrati random quote. else ispisi pass nije tacan. 4. 6. Logovati pogresan pass kada pokusaj nije tacan i u kom vremenu - format [TIMESTAMP] pass.(server)

(Razrada)

Naziv: RQG
Java
Package: ba.bitcamp.rqg.server - Generator.java ba.bitcamp.rqg.client - User.java
File location: Desktop (system agnostic)
Port: 1717
Repo: BITCampRandomQuoteGenerator
15 guotes
[yyyy-mm-dd h:i:s]
[D] - quote
Server IP input
pass [input] Client (Password handling (pass se proslijedi na klijentu))
pass final static (na serveru je pass zakucan)
quotes.txt (fajl iz kojeg citamo quotes)
auth_log.txt (dio na serveru koji loguje pogresne pokusaje) - [D] - pass - IP (java socket get IP - procitati)
recieved_quotes.txt Client