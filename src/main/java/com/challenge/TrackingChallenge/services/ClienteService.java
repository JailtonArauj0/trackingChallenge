package com.challenge.TrackingChallenge.services;

import com.challenge.TrackingChallenge.domain.Cliente.Cliente;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteDTO;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteFisica;
import com.challenge.TrackingChallenge.domain.Cliente.ClienteJuridica;
import com.challenge.TrackingChallenge.repositories.ClienteRepository;
import com.challenge.TrackingChallenge.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(ClienteDTO clienteDTO) {
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
    }

    public Cliente listarPorId(long id) {
        return clienteRepository.listarPorId(id);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public Cliente listarPorCnpj(String cnpj) {
        StringUtils validarCnpj = new StringUtils();
        if (validarCnpj.cnpjUtil(cnpj) != null) {
            return clienteRepository.listarPorCnpj(cnpj);
        }
        return null;
    }

    public Cliente listarPorCpf(String cpf) {
        StringUtils validarCpf = new StringUtils();
        if (validarCpf.cpfUtil(cpf) != null) {
            return clienteRepository.listarPorCpf(cpf);
        }
        return null;
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.atualizar(cliente);
    }

    public void deletarCliente(long id) {
        clienteRepository.deletar(id);
    }

}
