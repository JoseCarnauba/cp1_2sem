package br.com.fiap.cp1.controller;

import br.com.fiap.cp1.dto.TarefaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final List<TarefaDto> tarefas = new ArrayList<>();

    @PostMapping
    public ResponseEntity<TarefaDto> createTarefa(@RequestBody TarefaDto tarefaDto) {
        tarefas.add(tarefaDto);
        return ResponseEntity.ok(tarefaDto);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDto>> getAllTarefas() {
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto> getTarefaById(@PathVariable int id) {
        if (id < 0 || id >= tarefas.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarefas.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> updateTarefa(@PathVariable int id, @RequestBody TarefaDto tarefaDto) {
        if (id < 0 || id >= tarefas.size()) {
            return ResponseEntity.notFound().build();
        }
        tarefas.set(id, tarefaDto);
        return ResponseEntity.ok(tarefaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable int id) {
        if (id < 0 || id >= tarefas.size()) {
            return ResponseEntity.notFound().build();
        }
        tarefas.remove(id);
        return ResponseEntity.noContent().build();
    }
}
