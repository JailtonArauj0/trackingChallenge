package com.challenge.TrackingChallenge.services;

import com.challenge.TrackingChallenge.domain.Cliente.Cliente;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteDTO;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteFisica;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteJuridica;
import com.challenge.TrackingChallenge.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(ClienteDTO clienteDTO) {
        try {
            if (clienteDTO.tipoPessoa().getTipo().equalsIgnoreCase("PF")) {
                ClienteFisica pf = new ClienteFisica();
                pf.setNome(clienteDTO.nome());
                pf.setEndereco(clienteDTO.endereco());
                pf.setTelefone(clienteDTO.telefone());
                pf.setEmail(clienteDTO.email());
                pf.setCpf(clienteDTO.cpf());
                pf.setSexo(clienteDTO.sexo());
                pf.setDataNascimento(clienteDTO.dataNascimento());
                return clienteRepository.salvar(pf);
            } else {
                ClienteJuridica pj = new ClienteJuridica();
                pj.setNome(clienteDTO.nome());
                pj.setEndereco(clienteDTO.endereco());
                pj.setTelefone(clienteDTO.telefone());
                pj.setEmail(clienteDTO.email());
                pj.setCnpj(clienteDTO.cnpj());
                pj.setRazaoSocial(clienteDTO.razaoSocial());
                pj.setDataInscricao(clienteDTO.dataInscricao());
                return clienteRepository.salvar(pj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente listarPorCpf(String cpf){
        return clienteRepository.listarPorCpf(cpf);
    }

    public Cliente listarPorCnpj(String cnpj){
        return clienteRepository.listarPorCnpj(cnpj);
    }

    public Cliente atualizarCliente(Cliente cliente){
        return clienteRepository.atualizar(cliente);
    }

}
