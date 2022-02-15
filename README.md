<h1 align="center">
   TURING MACHINE
</h1>

A Turing machine is a mathematical model of computation that defines an abstract machine that manipulates symbols on a strip of tape according to a table of rules. Despite the model's simplicity, given any computer algorithm, a Turing machine capable of implementing that algorithm's logic can be constructed.

![image](https://www.researchgate.net/publication/341817215/figure/fig1/AS:897822048145408@1591068865717/Computational-model-of-a-Turing-machine.png)




# PROJECT GOALS

✅ Be able to decide whether or not a word is accepted given a specific machine  <br />

✅ Be able to perform function calculation <br />
(100% done)

# HOW TO EXECUTE

In this project you will find 5 pre made inputs inside the "input" folder.
In case you wanna test your own machine, just insert it using the following JSON design:

```
{
  "estados": ["your states here],  
  "alfabeto": "a string with the alphabet characters", 
  "alfabetoFita" : "a string with all the stack alphabet characters", 
  "regras": [  "All rules"
    {
      "estadoPartida": "q0", //First state
      "simbolo": "_",  //Symbol read on tape
      "colocado": "Z", //What will be written on the tape
      "estadosDestino": "q0", //Next state
      "dir": "R"  //The instruction you have to make on the tape (R or L)
    },  
    .  
    . 
    .
    //Rules 
  ],  
  "estadoInicial": "initial state", 
  "estadosFinais": [final states] 
} 
```

After setting up your machine, just execute and select it from the initial view.












