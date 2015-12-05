package app;

/**
 * Универсальный класс приложения, предоставляющий универсальные медоты шифрования по имени алгоритма.
 */
public class Application
{
    private CipherManager cipherManager = new CipherManager();

    public CipherManager getCipherManager()
    {
        return cipherManager;
    }
}
