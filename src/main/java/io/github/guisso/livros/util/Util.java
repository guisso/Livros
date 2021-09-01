/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Métodos auxiliares para situações gerais do sistema.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public class Util {

    /**
     * Realiza a conversão de um objeto do tipo Date para um objeto do tipo
     * LocalDate.
     *
     * @param data Objeto do tipo Date a ser convertido para LocalDate
     * @return Objeto LocalDate correspondente ao Date.
     */
    public static LocalDate convertDateToLocalDate(Date data) {

        if (data == null) {
            return LocalDate.now();
        }
        
        return new Date(data.getTime())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();


//        return data.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
    }

    /**
     * Realiza a conversão de um objeto do tipo Date para um objeto do tipo
     * LocalDateTime.
     *
     * @param data Objeto do tipo Date a ser convertido para LocalDateTime
     * @return Objeto LocalDateTime correspondente ao Date.
     */
    public static LocalDateTime convertDateToLocalDateTime(Date data) {

        if (data == null) {
            return LocalDateTime.now();
        }

        return new Date(data.getTime())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        
//        return data.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
    }
}
