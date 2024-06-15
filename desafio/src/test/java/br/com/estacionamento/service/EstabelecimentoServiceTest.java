package br.com.estacionamento.service;

import br.com.estacionamento.builder.EstabelecimentoRequestDTOBuilder;
import br.com.estacionamento.builder.EstabelecimentoResponseDTOBuilder;
import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.model.Estabelecimento;
import br.com.estacionamento.repository.EstabelecimentoRepository;
import br.com.estacionamento.service.exception.EstabelecimentoNotFoundException;
import br.com.estacionamento.service.exception.RegraNegocioException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoService estabelecimentoService;

    @InjectMocks
    private ModelMapper mapper;





    @Test
    void whenEstabelecimentoInformedThenItShouldBeCreated() throws RegraNegocioException {

        EstabelecimentoRequestDTO expectedEstabelecimentoDTO = EstabelecimentoRequestDTOBuilder.builder().build().toEstabelecimentoRequestDTO();
        Estabelecimento expectedSavedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedSavedEstabelecimento);
        lenient().when(estabelecimentoRepository.findByNome(expectedEstabelecimentoDTO.getNome())).thenReturn(empty());
        when(estabelecimentoRepository.save(expectedSavedEstabelecimento)).thenReturn(expectedSavedEstabelecimento);

        EstabelecimentoResponseDTO createdEstabelecimentoDTO = estabelecimentoService.cadastrar(expectedEstabelecimentoDTO);
        assertNotNull(createdEstabelecimentoDTO);

        assertThat(createdEstabelecimentoDTO.getNome(), is(equalTo(expectedEstabelecimentoDTO.getNome())));
        assertThat(createdEstabelecimentoDTO.getCnpj(), is(equalTo(expectedEstabelecimentoDTO.getCnpj())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getCep(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getCep())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getBairro(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getBairro())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getLogradouro(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getLogradouro())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getNumero(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getNumero())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getCidade(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getCidade())));
        assertThat(createdEstabelecimentoDTO.getEndereco().getEstado(), is(equalTo(expectedEstabelecimentoDTO.getEndereco().getEstado())));
        assertThat(createdEstabelecimentoDTO.getTelefone(), is(equalTo(expectedEstabelecimentoDTO.getTelefone())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasCarros(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasCarros())));
        assertThat(createdEstabelecimentoDTO.getQuantidadeVagasMotos(), is(equalTo(expectedEstabelecimentoDTO.getQuantidadeVagasMotos())));


    }


    @Test
    void whenListBeerIsCalledThenReturnAListOfBeers(){
        EstabelecimentoResponseDTO expectedEstabelecimentoDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();
        Estabelecimento expectedSavedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedSavedEstabelecimento);


        Pageable pageable = PageRequest.of(0,10);
        Page<Estabelecimento> estabelecimentoPage = new PageImpl<>(List.of(expectedSavedEstabelecimento), pageable, 1);
        when(estabelecimentoRepository.findAll(pageable)).thenReturn(estabelecimentoPage);

        Estabelecimento expectedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedEstabelecimento);

        Page<EstabelecimentoResponseDTO> estabelecimentoDTOPage = estabelecimentoService.listar(pageable);

        assertAll(
                () -> assertNotNull(estabelecimentoDTOPage),
                () -> assertNotNull(estabelecimentoDTOPage.getContent()),
                () -> assertFalse(estabelecimentoDTOPage.getContent().isEmpty()),
                () -> assertEquals(PageImpl.class, estabelecimentoDTOPage.getClass()),
                () -> assertEquals(1, estabelecimentoDTOPage.getTotalPages())
        );

        verify(estabelecimentoRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(estabelecimentoRepository);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> estabelecimentoService.listar(null));

        assertEquals("Paginação inválida!",exception.getMessage());

        assertThat(estabelecimentoDTOPage.getContent(),is(not(empty())));
        assertThat(estabelecimentoDTOPage.getTotalPages(),is(1));


    }

    @Test
    void whenGETListWithoutBeersIsCalledThenOkStatusIsReturned(){
        EstabelecimentoResponseDTO expectedEstabelecimentoDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();
        Estabelecimento expectedSavedEstabelecimento = mapper.map(expectedEstabelecimentoDTO, Estabelecimento.class);
        assertNotNull(expectedSavedEstabelecimento);


        Long id = 1L;
        when(estabelecimentoRepository.findById(id)).thenReturn(Optional.empty());

        EstabelecimentoNotFoundException exception = assertThrows(EstabelecimentoNotFoundException.class,()-> estabelecimentoService.listarPorId(id));
        assertEquals("Estabelecimento não encontrado",exception.getMessage());

        verify(estabelecimentoRepository, times(1)).findById(id);

    }

    @Test
    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted(){
        EstabelecimentoResponseDTO expectedEstabelecimentoDTO = EstabelecimentoResponseDTOBuilder.builder().build().toEstabelecimentoResponseDTO();

        doNothing().when(estabelecimentoRepository).deleteById(expectedEstabelecimentoDTO.getId());

        estabelecimentoService.excluir(expectedEstabelecimentoDTO.getId());

        verify(estabelecimentoRepository,times(1)).deleteById(expectedEstabelecimentoDTO.getId());

    }

}
