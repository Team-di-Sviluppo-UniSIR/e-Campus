package CardMgmt;

public interface CardInfoIF {

	/**
	 * Ritorna il bilancio della carta di un dato utente.
	 *
	 * @param userId l'identificativo dell'utente
	 * @return il saldo della carta richiesto
	 */
	public double getBalance(String userId);
	
	/**
	 * Ritorna il bilancio incrementato della carta di un dato utente.
	 *
	 * @param userId l'identificativo dell'utente
	 * @param sumToAdd la somma da aggiungere al saldo
	 * @return il saldo della carta aggiornato e opportunamente incrementato
	 */
	public double incrementBalance(String userId, double sumToAdd);
	
	/**
	 * Ritorna il bilancio decrementato della carta di un dato utente.
	 *
	 * @param userId l'identificativo dell'utente
	 * @param sumToSubtract la somma da sottrarre al saldo
	 * @return il saldo della carta aggiornato e opportunamente decrementato
	 */
	public double reduceBalance(String userId, double sumToSubtract);

}
