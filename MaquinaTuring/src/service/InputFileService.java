package service;

import data.Machine;
import data.Rule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileService {

    JSONArray jsonArray;
    JSONObject jsonField;

    public Machine parseJSON() throws Exception {
        File workingDirectory = new File(System.getProperty("user.dir"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                return parseFile(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (Exception e) {
                throw new Exception("Arquivo inválido!");
            }
        }
        return null;
    }

    private Machine parseFile(String absolutePath) throws IOException, ParseException {

        jsonField = (JSONObject) new JSONParser().parse(new FileReader(absolutePath));

        jsonArray = (JSONArray) jsonField.get("estados");
        List<String> states = parseArrayField(jsonArray);

        String alphabet = parseAlphabet();
        String tapeAlphabet = parseTapeAlphabet();
        List<Rule> Rules = parseRules();
        String initialState = parseInitialState();

        jsonArray = (JSONArray) jsonField.get("estadosFinais");
        List<String> finalStates = parseArrayField(jsonArray);


        return new Machine(states, alphabet, tapeAlphabet, Rules, initialState,finalStates);
    }

    private List<String> parseArrayField(JSONArray jsonArray) {

        List<String> array = new ArrayList<>();
        for (Object o : jsonArray) {

            array.add((String) o);
        }
        return array;
    }

    private String parseAlphabet() {
        return (String) jsonField.get("alfabeto");
    }

    private List<Rule> parseRules() {

        List<Rule> Rules = new ArrayList<>();

        jsonArray = (JSONArray) jsonField.get("regras");

        for (Object rule : jsonArray) {

            JSONObject jsonRule = (JSONObject) rule;

            String sourceState = (String) jsonRule.get("estadoPartida");

            String symbol = (String) jsonRule.get("simbolo");

            String tapeSymbol = (String) jsonRule.get("colocado");

            String targets = (String) jsonRule.get("estadosDestino");

            String direction = (String) jsonRule.get("dir");

            Rules.add(new Rule(sourceState, symbol.charAt(0), targets, tapeSymbol, direction));
        }
        return Rules;
    }

    private String parseInitialState() {
        return (String) jsonField.get("estadoInicial");
    }

    private String parseTapeAlphabet() {
        return (String) jsonField.get("alfabetoFita");
    }

}


