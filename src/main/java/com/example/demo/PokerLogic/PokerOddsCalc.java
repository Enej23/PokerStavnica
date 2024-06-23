package com.example.demo.PokerLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokerOddsCalc {

    public void setWinningOddsAfterFlop(Game game){
        StringBuilder commandBuilder = new StringBuilder("poker-odds-calc -e -b " +game.getBoard() );
        for (Player player : game.getPlayers()) {
            commandBuilder.append(" -p ").append(player.getCard1()).append(player.getCard2());
        }
        String command = commandBuilder.toString();

        System.out.println(commandBuilder);
        // Create ProcessBuilder
        setOdds(command,game);
    }


    public void setWinningOddsBeforeFlop(Game game) {
            // Constructing the command based on player hands
            StringBuilder commandBuilder = new StringBuilder("poker-odds-calc -e");
            for (Player player : game.getPlayers()) {
                commandBuilder.append(" -p ").append(player.getCard1()).append(player.getCard2());
            }
            String command = commandBuilder.toString();

            System.out.println(commandBuilder);
            // Create ProcessBuilder
            setOdds(command,game);

    }

    public void setOdds(String command,Game game){
            try{
            ProcessBuilder processBuilder = new ProcessBuilder();
            // Set the command and any arguments
            processBuilder.command("cmd", "/c", command);

            // Start the process
            Process process = processBuilder.start();

            // Read output
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Define a pattern for matching the win percentage
            // Define the regex pattern to match the win percentage
                //
            Pattern pattern = Pattern.compile("\\s(\\d+(?:\\.\\d+)?)%");

            String line;
            int playerIndex = 0;
            while ((line = reader.readLine()) != null) {
                // Match the pattern against the input line
                Matcher matcher = pattern.matcher(line);
                // Iterate over matches and extract double values
                if(line.startsWith(" Royal")){
                    break;
                }
                if(line.contains("100.00%")){
                    Player player = game.getPlayers().get(playerIndex);
                    player.setOddsToWin(100);
                    playerIndex++;
                }
                else {
                    while (matcher.find()) {
                        double percentage = Double.parseDouble(matcher.group(1));
                        Player player = game.getPlayers().get(playerIndex);
                        player.setOddsToWin((float) percentage);
                        playerIndex++;
                        break; // Exit the loop after extracting the first percentage
                    }
                }
            }
            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Exited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
