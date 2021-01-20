package Authentication;

public interface MobileAuthenticationIF {

	/**
	 * Ritorna l'esito della richiesta di login
	 *
	 * @param user     l'username inserito dall'utente
	 * @param password la password inserita dall'utente
	 * @return true se il login è stato eseguito correttamente, false in caso
	 *         contrario
	 */
	public boolean logIn(String user, String password);

	/**
	 * Ritorna l'esito della richiesta di logout
	 *
	 * @param user     l'username inserito dall'utente
	 * @param password la password inserita dall'utente
	 * @return true se il logout è stato eseguito correttamente, false in caso
	 *         contrario
	 */
	public boolean logOut(String user, String password);

	/**
	 * Ritorna l'esito della richiesta di cambiamento della password
	 *
	 * @param user        l'username inserito dall'utente
	 * @param oldPassword la vecchia password inserita dall'utente
	 * @param newPassword la nuova password inserita dall'utente
	 * @return true se l'operazione è stata eseguita correttamente, false in caso
	 *         contrario
	 */
	public boolean setNewPassowrd(String user, String oldPassword, String newPassword);

	/**
	 * Ritorna l'esito della richiesta di inserimento di un nuovo utente
	 *
	 * @param name      il nome del nuovo utente da registrare
	 * @param surname   il cognome del nuovo utente da registrare
	 * @param unibgMail la mail universitaria del nuovo utente da registrare
	 * @param user      l'username del nuovo utente da registrare
	 * @param password  la password del nuovo utente da registrare
	 * @return true se l'operazione è stata eseguita correttamente, false in caso
	 *         contrario
	 */
	public boolean registerNewUser(String name, String surname, String unibgMail, String user, String Password);
}
