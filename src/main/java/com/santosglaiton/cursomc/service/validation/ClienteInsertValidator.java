package com.santosglaiton.cursomc.service.validation;

import com.santosglaiton.cursomc.DTO.ClienteNewDTO;
import com.santosglaiton.cursomc.controller.exceptions.FieldMessage;
import com.santosglaiton.cursomc.domain.enums.TipoCliente;
import com.santosglaiton.cursomc.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert ann){
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

    if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
        list.add(new FieldMessage("cpfOuCnpj", "CPF inválido" ));
    }

    if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
        list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
    }

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }


}
