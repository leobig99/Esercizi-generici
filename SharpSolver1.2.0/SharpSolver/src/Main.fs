﻿(*
 * SharpSolver - Progetto di Programmazione e Calcolo a.a. 2018-19
 * Main.fs: console e codice main
 * (C) 2018 Alvise Spano' @ Universita' Ca' Foscari di Venezia
 *)

module SharpSolver.Main

open Microsoft.FSharp.Text.Lexing
open Absyn
open System
open Prelude
open Microsoft.FSharp.Text


// funzioni di logging e printing
//

let hout hd fmt =
    if not <| String.IsNullOrWhiteSpace hd then
        printf "[%s]%s" hd (new String (' ', Config.prefix_max_len - String.length hd))
        stdout.Flush ()
    printfn fmt

let chout col hd fmt =
    let c = Console.ForegroundColor
    Console.ForegroundColor <- col
    Printf.kprintf (fun s -> hout hd "%s" s; Console.ForegroundColor <- c) fmt

let out fmt = hout "" fmt
let cout col fmt = chout col "" fmt

let norm fmt = chout ConsoleColor.Blue "norm" fmt
let derived fmt = chout ConsoleColor.Magenta "derive" fmt
let sol fmt = chout ConsoleColor.Green "sol" fmt
let error fmt = chout ConsoleColor.Red "error" fmt    
let identity fmt = chout ConsoleColor.Green "identity" fmt    

// interprete dei comandi e delle espressioni
//

let interpreter_loop () =
    while true do
        printf "\n%s" Config.prompt_prefix          // stampa il prompt
        stdout.Flush ()                             // per sicurezza flusha lo stdout per vedere la stampa del prompt senza end-of-line
        let input = Console.ReadLine ()             // leggi l'input scritto dall'utente
        let lexbuf = LexBuffer<_>.FromString input  // crea un lexbuffer sulla stringa di input

        // funzione locale per il pretty-printing degli errori
        let localized_error msg =
            let tabs = new string (' ', Config.prompt_prefix.Length + lexbuf.StartPos.Column)
            let cuts = new string ('^', let n = lexbuf.EndPos.Column - lexbuf.StartPos.Column in if n > 0 then n else 1)
            cout ConsoleColor.Yellow "%s%s\n" tabs cuts
            error "error at %d-%d: %s" lexbuf.StartPos.Column lexbuf.EndPos.Column msg 
        
        // blocco con trapping delle eccezioni
        try
            let line = Parser.line Lexer.tokenize lexbuf    // invoca il parser sul lexbuffer usando il lexer come tokenizzatore

            #if DEBUG
            hout "Absyn" "%+A" line
            hout "Pretty" "%O" line
            #endif

            // interpreta la linea in base al valore di tipo line prodotto dal parsing
            match line with
            | Cmd "help" ->
                out "%s" Config.help_text

            | Cmd ("quit" | "exit") ->
                out "%s" Config.exit_text
                exit 0

            // TODO: se volete supportare altri comandi, fatelo qui (opzionale)
            
            | Cmd s -> error "unknown command: %s" s    // i comandi non conosciuti cadono in questo caso

            // TODO: aggiungere qui sotto i pattern per i casi Expr ed Equ con relativo codice per, rispettivamente, normalizzare espressioni e risolvere equazioni
            | Expr e -> 
                let pol = Impl.reduce e
                hout "Derived" " %s " (pol.ToString())
                let pol_norm=(Impl.normalize pol)
                hout "Norm" " %s " (pol_norm.ToString())
                let grado=Impl.polynomial_degree pol
                hout "Degree" " %d " (grado)

            | Equ (e1,e2) ->
                let p1 = Impl.reduce e1
                let p2 = Impl.reduce e2
                let pol = Impl.polinomioEquazione(p1,p2)
                hout "Derived" " %s " ((( Impl.normalize p1).ToString()) + " = " +  (( Impl.normalize p2).ToString()))
                let pol_norm = Impl.normalize pol
                hout "Norm" " %s = 0 " (pol_norm.ToString()) 
                let grado = Impl.normalized_polynomial_degree pol_norm
                hout "Degree" " %d " (grado)
                match grado with
                     0 -> printfn "[Identity] %b " (Impl.solve0 pol_norm)
                    |1 -> hout "Sol" " x = %s " ((Impl.solve1 pol_norm).ToString())
                    |2 -> hout "Sol" " %s " ((Impl.solve2 pol_norm).ToString())
                    |_-> raise (NotImplementedException (sprintf "unknown command or expression: %O" line))
                  
                    
            | _ -> raise (NotImplementedException (sprintf "unknown command or expression: %O" line))
                   
        // gestione delle eccezioni
        with LexYacc.ParseErrorContextException ctx ->
                let ctx = ctx :?> Parsing.ParseErrorContext<Parser.token>
                localized_error (sprintf "syntax error%s" (match ctx.CurrentToken with Some t -> sprintf " at token <%O>" t | None -> ""))

           | Lexer.LexerError msg -> localized_error msg 

           | :? NotImplementedException as e -> error "%O" e
        
           | e -> localized_error e.Message


// funzione main: il programma comincia da qui
//

[<EntryPoint>]
let main _ = 
    let code =
        try
            interpreter_loop ()                 // chiama l'interprete
            0                                   // ritorna il codice di errore 0 (nessun errore) al sistema operativo se tutto è andato liscio
        with e -> error "fatal error: %O" e; 1  // in caso di eccezione esce con codice di errore 1
    #if DEBUG
    Console.ReadKey () |> ignore                // aspetta la pressione di un tasto prima di chiudere la finestra del terminare 
    #endif
    code


