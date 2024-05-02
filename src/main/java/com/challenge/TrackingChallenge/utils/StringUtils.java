package com.challenge.TrackingChallenge.utils;

import com.challenge.TrackingChallenge.exception.CustomException;

public class StringUtils {

    public String cpfUtil(String cpf){
        String cpfLimpo = cpf.replaceAll("\\D", "");
        if(cpfLimpo.length() != 11){
            throw new CustomException("O CPF deve ter exatamente 11 caracteres");
        }
        return cpfLimpo;
    }

    public String cnpjUtil(String cnpj){
        String cnpjLimpo = cnpj.replaceAll("\\D", "");
        if(cnpjLimpo.length() != 14){
            throw new CustomException("O CNPJ deve ter exatamente 14 caracteres");
        }
        return cnpjLimpo;
    }
}
