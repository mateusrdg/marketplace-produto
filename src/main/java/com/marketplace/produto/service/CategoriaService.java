package com.marketplace.produto.service;
import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.constantes.Excecoes;
import com.marketplace.produto.model.dto.CategoriaDTO;
import com.marketplace.produto.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {


    private CategoriaRepository categoriaRepository;

    private  ModelMapper modelMapper;


    public CategoriaService(CategoriaRepository categoriaRepository,ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;

    }

    public List<CategoriaDTO> list() {
        List<Categoria> listar = categoriaRepository.findAll();
        return listar.stream().map(x -> modelMapper.map(x, CategoriaDTO.class)).collect(Collectors.toList());
    }

    public CategoriaDTO buscar(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(Excecoes.CATEGORIA_NAO_ENCONTRADA));
        CategoriaDTO categoriaDTO = modelMapper.map(categoria, com.marketplace.produto.model.dto.CategoriaDTO.class);
        return categoriaDTO;
    }

    public Categoria salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        associarDados(categoria);
        return categoriaRepository.save(categoria);

    }

    public void atualizarCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        associarDados(categoria);
        categoriaRepository.save(categoria);
    }

    public void remover(Long id){
        categoriaRepository.deleteById(id);
    }

    private void associarDados(Categoria categoria) {

    }
}
