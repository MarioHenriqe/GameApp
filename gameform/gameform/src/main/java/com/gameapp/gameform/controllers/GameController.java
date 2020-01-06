package com.gameapp.gameform.controllers;

import com.gameapp.gameform.models.Equipe;
import com.gameapp.gameform.models.Game;
import com.gameapp.gameform.repository.EquipeRepository;
import com.gameapp.gameform.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.Console;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @RequestMapping(value = "/cadastrarGame", method = RequestMethod.GET)
    public String form(){
        return "formGame";
    }

    @RequestMapping(value = "/cadastrarGame", method = RequestMethod.POST)
    public String form(@Valid Game game,BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifque os campos");
            return "redirect:/cadastrarGame";
        }

        gameRepository.save(game);
        attributes.addFlashAttribute("mensagem", "Jogo adicionado com sucesso");
        return "redirect:/cadastrarGame";
    }

    @RequestMapping("/games")
    public ModelAndView listaGames(){
        try {
            ModelAndView mv = new ModelAndView("index");
            Iterable<Game> games = gameRepository.findAll();
            mv.addObject("games", games);
            return mv;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesGame(@PathVariable("id") long id){
        Game game = gameRepository.findById(id);
        ModelAndView mv = new ModelAndView("detalhesGame");
        mv.addObject("game", game);

        Iterable<Equipe> equipe = equipeRepository.findByGame(game);
        mv.addObject("equipe", equipe);
        return mv;
    }

    @RequestMapping("/deletarGame")
    public String deletarGame(long id){
        Game game = gameRepository.findById(id);
        gameRepository.delete(game);
        return "redirect:/games";
    }

    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Equipe equipe = equipeRepository.findById(id);
        equipeRepository.delete(equipe);
        Game game = equipe.getGame();
        long idGame = game.getId();
        String idString = "" + idGame;
        return "redirect:/" + idString;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String detalhesGamePost(@PathVariable("id") long id, @Valid Equipe funcionario, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifque os campos");
            return "redirect:/{id}";
        }

        Game game = gameRepository.findById(id);
        funcionario.setGame(game);
        equipeRepository.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Membro da equipe cadastrado com sucesso");
        return "redirect:/{id}";
    }

}
