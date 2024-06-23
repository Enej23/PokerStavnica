package com.example.demo.controller;

import com.example.demo.PokerLogic.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private Gameplay gameplay = new Gameplay(10000,0);
    private Game game;
    private PokerOddsCalc pokerOddsCalc = new PokerOddsCalc();
    public HomeController() {

        this.game = new Game(6);
        this.pokerOddsCalc.setWinningOddsBeforeFlop(this.game);
    }

    @GetMapping("/")
    public String home(Model model) {
        // Add image filenames to the model
        List<String> imageFilenames = new ArrayList<>();
        List<String> quotas = new ArrayList<>();
        for(Player player : game.getPlayers()){
            imageFilenames.add(player.getCard1AsPNG());
            imageFilenames.add(player.getCard2AsPNG());
            quotas.add(Float.toString(player.getQuotaToWin()));
        }
        model.addAttribute("sumOfBets",game.getSumOfGameBets());
        model.addAttribute("bets", game.getBets());
        model.addAttribute("imageFilenames", imageFilenames);
        if(game.getBoard().length()==10) {
            model.addAttribute("quotas", new ArrayList<String>() {{ add(""); add(""); add(""); add(""); add("");add(""); }});

            int winCounter = 0;
            List<Player> players = game.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getOddsToWin() == 100) {
                    winCounter++;
                    if(winCounter ==2){
                        model.addAttribute("winner", "Tie");
                        game.setIndexOfGameWinner(-1);
                        break;
                    }
                    model.addAttribute("winner", "Player " + (i + 1));
                    game.setIndexOfGameWinner(i);
                }
            }
            float sumMoneyWon = 0;
            for(Bet bet: game.getBets()){
                if(bet.getPlayerIndex()== game.getIndexOfGameWinner()){
                    float moneyWon = bet.getBet()*bet.getQuota();
                    gameplay.setBalance(gameplay.getBalance() + moneyWon);
                    gameplay.setCasinoBalance(gameplay.getCasinoBalance()-moneyWon);
                    sumMoneyWon += moneyWon;
                }
            }
            game.setLastGamePayout(sumMoneyWon);
            model.addAttribute("sumMoneyWon", game.getLastGamePayout());
        }
        else{
            model.addAttribute("quotas",quotas);
            model.addAttribute("winner", "");
            model.addAttribute("sumMoneyWon", game.getLastGamePayout());

        }

        model.addAttribute("balance",gameplay.getBalance());
        model.addAttribute("casinobalance",gameplay.getCasinoBalance());
        String board = game.getBoard();
        switch (game.getBoard().length()){
            case 0:
                model.addAttribute("boardCards", Arrays.asList("rj.png","rj.png","rj.png","rj.png","rj.png"));
                break;
            case 6:
                model.addAttribute("boardCards", Arrays.asList(board.substring(0, 2) + ".png", board.substring(2, 4) + ".png", board.substring(4, 6) + ".png", "rj.png", "rj.png"));
                break;
            case 8:
                model.addAttribute("boardCards", Arrays.asList(board.substring(0, 2) + ".png", board.substring(2, 4) + ".png", board.substring(4, 6) + ".png", board.substring(6, 8) + ".png", "rj.png"));
                break;
            case 10:
                model.addAttribute("boardCards", Arrays.asList(board.substring(0, 2) + ".png", board.substring(2, 4) + ".png", board.substring(4, 6) + ".png", board.substring(6, 8) + ".png", board.substring(8, 10) + ".png"));
                break;
        }
        return "home"; // This will return the view named "home.html"
    }
    @GetMapping("/redeal")
    public String redeal(Model model) {

        this.game = new Game(6); // Reset the game with 6 players
        this.pokerOddsCalc.setWinningOddsBeforeFlop(this.game);
        model.addAttribute("balance",gameplay.getBalance());
        model.addAttribute("casinobalance",gameplay.getCasinoBalance());
        model.addAttribute("winner","");
        model.addAttribute("sumMoneyWon", game.getLastGamePayout());
        model.addAttribute("sumOfBets",game.getSumOfGameBets());

        // Add image filenames to the model for the new game
        List<String> imageFilenames = new ArrayList<>();
        List<Float> quotas = new ArrayList<>();
        for(Player player : game.getPlayers()){
            imageFilenames.add(player.getCard1AsPNG());
            imageFilenames.add(player.getCard2AsPNG());
            quotas.add(player.getQuotaToWin());
        }
        model.addAttribute("imageFilenames", imageFilenames);
        model.addAttribute("quotas",quotas);
        model.addAttribute("boardCards", Arrays.asList("rj.png","rj.png","rj.png","rj.png","rj.png"));

        return "home"; // Return the view named "home.html"
    }
    @GetMapping("/deal-flop")
    public String dealFlop(Model model) {
        if(game.getBoard().length()==0){
            game.addFlop();
            pokerOddsCalc.setWinningOddsAfterFlop(this.game);
        }
        return "redirect:/"; // Redirect back to the home page
    }

    @GetMapping("/deal-turn")
    public String dealTurn(Model model) {
        if(game.getBoard().length()==6){
            game.addTurn();
            pokerOddsCalc.setWinningOddsAfterFlop(this.game);
        }
        // Add necessary attributes to the model
        return "redirect:/"; // Redirect back to the home page
    }

    @GetMapping("/deal-river")
    public String dealRiver(Model model) {
        if(game.getBoard().length()==8){
            game.addRiver();
            pokerOddsCalc.setWinningOddsAfterFlop(this.game);
        }
        // Add necessary attributes to the model
        return "redirect:/"; // Redirect back to the home page
    }

    @PostMapping("/bet-on-player")
    public String betOnPlayer(@RequestParam("betAmount") float betAmount,
                              @RequestParam("playerIndex") int playerIndex) {
        // Your logic to handle the bet on the player
        // You can use the betAmount and playerIndex parameters here

        gameplay.setBalance(gameplay.getBalance()-betAmount);
        gameplay.setCasinoBalance(gameplay.getCasinoBalance()+betAmount);
        game.setSumOfGameBets(game.getSumOfGameBets()+betAmount);

        game.addBet(betAmount,game.getPlayers().get(playerIndex).getQuotaToWin(),playerIndex);
        // For demonstration purposes, you can simply print them
        System.out.println("Bet Amount: " + betAmount);
        System.out.println("Player Index: " + playerIndex);

        // Redirect to a confirmation page or any other page after processing the bet
        return "redirect:/";
    }
}
