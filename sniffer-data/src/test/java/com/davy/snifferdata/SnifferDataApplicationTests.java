package com.davy.snifferdata;

import com.davy.snifferdata.models.Log;
import com.davy.snifferdata.repositories.LogRepository;
import com.davy.snifferdata.services.LogService;
import com.davy.snifferdata.services.NotifierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LogServiceTest {

    private LogRepository repository;
    private LogService service;
    private NotifierService notifier;

    @BeforeEach
    void setUp() {
        repository = mock(LogRepository.class);
        notifier = mock(NotifierService.class);
        service = new LogService(repository, notifier);
    }

    @Test
    void saveLog_ShouldSaveLogAndTriggerAlert_WhenErrorLevel() {
        Log log = new Log("Erro crítico", "ERROR");

        when(repository.save(any(Log.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Log saved = service.saveLog(log);

        // Verifica se log foi retornado corretamente
        assertEquals("Erro crítico", saved.getMessage());
        assertEquals("ERROR", saved.getLevel());

        // Verifica se alert foi chamado
        verify(notifier, times(1)).alert("Novo log crítico: Erro crítico");

        // Verifica se o log foi passado para o repositório
        ArgumentCaptor<Log> captor = ArgumentCaptor.forClass(Log.class);
        verify(repository).save(captor.capture());
        assertEquals("Erro crítico", captor.getValue().getMessage());
    }

    @Test
    void saveLog_ShouldSaveLogWithoutAlert_WhenInfoLevel() {
        Log log = new Log("Tudo certo", "INFO");

        when(repository.save(any(Log.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Log saved = service.saveLog(log);

        assertEquals("Tudo certo", saved.getMessage());
        assertEquals("INFO", saved.getLevel());

        // Não deve chamar alert
        verify(notifier, never()).alert(anyString());

        // Deve salvar no repositório
        verify(repository, times(1)).save(log);
    }

    @Test
    void getAllLogs_ShouldReturnAllLogs() {
        List<Log> logs = List.of(
                new Log("Erro 1", "ERROR"),
                new Log("Info 1", "INFO")
        );

        when(repository.findAll()).thenReturn(logs);

        List<Log> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals("Erro 1", result.get(0).getMessage());
        assertEquals("Info 1", result.get(1).getMessage());

        verify(repository, times(1)).findAll();
    }
}
