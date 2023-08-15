package br.com.picpay.dtos;

import br.com.picpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String password, String document, UserType userType, BigDecimal balance, String email)
{
}
