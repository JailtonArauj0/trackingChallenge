package com.challenge.TrackingChallenge.services;

import com.challenge.TrackingChallenge.domain.Veiculo.Veiculo;
import com.challenge.TrackingChallenge.domain.Veiculo.VeiculoDTO;
import com.challenge.TrackingChallenge.exception.CustomException;
import com.challenge.TrackingChallenge.repositories.ClienteRepository;
import com.challenge.TrackingChallenge.repositories.VeiculoRepository;
import com.challenge.TrackingChallenge.utils.ValidarVeiculoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Veiculo cadastrar(VeiculoDTO veiculoDTO) {
        ValidarVeiculoUtil validarVeiculo = new ValidarVeiculoUtil(veiculoRepository, clienteRepository);
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(veiculoDTO, veiculo);
        if (validarVeiculo.validarVeiculo(veiculo)) {
            return veiculoRepository.salvar(veiculo);
        }
        return null;
    }

    public VeiculoDTO listarPorId(long id){
        Veiculo veiculo = veiculoRepository.listarPorId(id);
        VeiculoDTO veiculoDTO = new VeiculoDTO(veiculo);
        return veiculoDTO;
    }

    public List<VeiculoDTO> listarTodosPorIdCliente(long idCliente){
        List<Veiculo> veiculos = veiculoRepository.listarTodosPorIdCLiente(idCliente);
        List<VeiculoDTO> veiculoDTOS = new ArrayList<>();
        for (Veiculo veiculo : veiculos){
            VeiculoDTO veiculoDTO = new VeiculoDTO(veiculo);
            veiculoDTOS.add(veiculoDTO);
        }
        return veiculoDTOS;
    }

    public VeiculoDTO listarPorParametros(String placa, String renavam, String chassi){
        Veiculo veiculo = veiculoRepository.listarPorParametros(placa, renavam, chassi);
        VeiculoDTO veiculoDTO = new VeiculoDTO(veiculo);
        return veiculoDTO;
    }

    public Veiculo atualizar(VeiculoDTO veiculoDTO){
        if(veiculoDTO.id() <= 0 || veiculoDTO.idCliente() <= 0){
            throw new CustomException("Informe valores válidos para ID ou ID Cliente");
        }
        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(veiculoDTO, veiculo);
        return veiculoRepository.atualizar(veiculo);
    }

    public void deletar(long id){
        veiculoRepository.deletar(id);
    }

}
