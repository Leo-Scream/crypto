package ru.scream.crypto.crypto;

import ru.scream.crypto.crypto.cipher.Atbash;
import ru.scream.crypto.crypto.cipher.Caesar;
import ru.scream.crypto.crypto.cipher.Cipher;
import ru.scream.crypto.crypto.cipher.Vigenere;

/**
 * @author Verhoturov Denis - Leo.Scream.
 */
public enum Ciphers
{
    CAESAR(Caesar.class), VIGENERE(Vigenere.class), ATBASH(Atbash.class);

    private final Class <? extends Cipher> cipherClass;
    private Cipher instance;

    Ciphers(Class <? extends Cipher> cipherClass)
    {
        this.cipherClass = cipherClass;
    }

    public Cipher getInstance()
    {
        if (this.instance == null) {
            try {
                this.instance = this.cipherClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return this.instance;
    }
}
