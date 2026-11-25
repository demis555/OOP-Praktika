package biblioteka.exception;

/**
 * Metama, kai naudotojo įvesti duomenys neteisingi (tušti laukai, blogas el. paštas ir pan.).
 * Paveldi RuntimeException, todėl nereikia visur rašyti "throws".
 */
public class NetinkamaIvestisException extends RuntimeException {

    public NetinkamaIvestisException(String zinute) {
        super(zinute);
    }
}
