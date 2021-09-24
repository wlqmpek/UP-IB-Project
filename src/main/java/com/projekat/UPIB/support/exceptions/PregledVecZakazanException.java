package com.projekat.UPIB.support.exceptions;

public class PregledVecZakazanException extends RuntimeException {

    public PregledVecZakazanException() {
        super("Dati pregled je vec zakazan od strane drugog korisnika!");
    }

}

