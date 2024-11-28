package ifrn.pi.eventos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private EventoRepository er;

	@GetMapping("/form")
	public String form() {
		return "eventos/FormEvento";

	}

	@PostMapping
	public String adicionar(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		
		return "eventos/evento-adicionado";
	}
	
	@GetMapping
	public ModelAndView listar() {
		
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	  @GetMapping("/{id}")
		public ModelAndView detalhar( @PathVariable Long id) {
	    	ModelAndView md = new ModelAndView();
			Optional<Evento> opt = er.findById(id);
			if(opt.isEmpty()) {
				md.setViewName("redirect:/eventos");
				return md;
			}
			
			md.setViewName("eventos/detalhes");
			Evento evento = opt.get();
			md.addObject("evento", evento);
			
			return md;
	    }

	
}