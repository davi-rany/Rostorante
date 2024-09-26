//package com.example.rostorante.service;
//
//import com.example.rostorante.model.Usuario;
//import com.example.rostorante.repository.UsuarioRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UsuarioServiceTest {
//
//    @InjectMocks
//    private UsuarioService usuarioService;
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
//    }
//
//    @Test
//    void registrarUsuario_deveCriptografarSenhaESalvarUsuario() {
//        // Arrange
//        Usuario usuario = new Usuario();
//        usuario.setNome("Davi");
//        usuario.setEmail("davi@example.com");
//        usuario.setSenha("senhaSegura");
//
//        // Simula a criptografia da senha
//        when(passwordEncoder.encode("senhaSegura")).thenReturn("senhaCriptografada");
//        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
//
//        // Act
//        Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
//
//        // Assert
//        assertNotNull(novoUsuario);
//        assertEquals("senhaCriptografada", novoUsuario.getSenha());
//        verify(passwordEncoder, times(1)).encode("senhaSegura");
//        verify(usuarioRepository, times(1)).save(usuario);
//    }
//
//    @Test
//    void buscarPorEmail_deveRetornarUsuarioSeEncontrado() {
//        // Arrange
//        Usuario usuario = new Usuario();
//        usuario.setEmail("davi@example.com");
//
//        when(usuarioRepository.findByEmail("davi@example.com")).thenReturn(Optional.of(usuario));
//
//        // Act
//        Usuario usuarioEncontrado = usuarioService.buscarPorEmail("davi@example.com");
//
//        // Assert
//        assertNotNull(usuarioEncontrado);
//        assertEquals("davi@example.com", usuarioEncontrado.getEmail());
//        verify(usuarioRepository, times(1)).findByEmail("davi@example.com");
//    }
//
//    @Test
//    void buscarPorEmail_deveLancarExcecaoSeUsuarioNaoEncontrado() {
//        // Arrange
//        when(usuarioRepository.findByEmail("naoexiste@example.com")).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            usuarioService.buscarPorEmail("naoexiste@example.com");
//        });
//
//        assertEquals("Usuário não encontrado", exception.getMessage());
//        verify(usuarioRepository, times(1)).findByEmail("naoexiste@example.com");
//    }
//}
