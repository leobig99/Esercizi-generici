
(*
 * SharpSolver - Progetto di Programmazione e Calcolo a.a. 2018-19
 * Impl.fsi: implementazioni degli studenti
 * (C) 2018 Alvise Spano' @ Universita' Ca' Foscari di Venezia
 *)

module SharpSolver.Impl

open Absyn
open Prelude
open System

(* let lunghezzaPeriodo (n : string []) : int = 
    let mutable ris = 0
    let mutable newj = 0 
    for i = 1 to (n.Length)/2 do
        let mutable count = 0
        for j = 0 to  (n.Length) - 2  do
            if n.[j] = n.[j+i] then
                if (count = (i + 1) ) then ris <- i
                else count <- count + 1 
            else 
                newj <- newj + 1 
                j = newj
                count <- 0
    ris           

let rationalize (x : float) : rational =
    let a = (string x).Split('.')
    let x = int a.[0]
    if a.[1] = "" then rational (x,1)
    else 
        let y = int a.[1]
        let mutable app = 0.0
        let l =( lunghezzaPeriodo ((a.[1]).Split(' ')))
        for i = 0 to l do
            app <- app + 9.0*(10.0**float i)
        //trovare numeratore stringa x unito y -la parte senza periodico
        rational (int x*(int ((10.0)**(9.0))),int(app*(10.0)**(float (y-l))))
*)

let rationalize (x : float) : rational =
    rational (int x*(int ((10.0)**(9.0))),int((10.0)**9.0))

let monomial_degree (m : monomial) : int = 
    match m with
    |Monomial (_,n)->n
    |Monomial (q,_) when q=0Q -> 1
    |Monomial (q,_) when q=1Q -> 1


let monomial_negate (m : monomial) : monomial = 
    match m with
        | Monomial (q,n) when q = 0Q -> Monomial (q,n)
        | Monomial (q,n) -> Monomial (-q,n)

let polynomial_degree (p : polynomial) : int = 
    match p with
    |Polynomial []->0
    |Polynomial [m]-> monomial_degree m
    |Polynomial (x::xs)-> 
        let rec getMax degree list =
            match list with
            []-> degree
            |x::xs ->
                let grado = monomial_degree x
                in if grado > degree then getMax grado xs
                   else getMax degree xs
        in getMax 0 (x::xs)

let rec polynomial_negate (p : polynomial) : polynomial = 
    match p with
    |Polynomial [x] -> Polynomial [monomial_negate x]
    |Polynomial l -> Polynomial (l|>List.map(monomial_negate))


let normalized_polynomial_degree (np : normalized_polynomial) : int =
    let (NormalizedPolynomial a) = np
    let rec semplificaArray l =
        match l with
        []->[0Q]
        |x::xs->
            if x = 0Q then semplificaArray xs 
            else l
    in List.length (semplificaArray (List.rev(Array.toList a))) - 1

let sommaFrazioni (r:rational) (r1:rational) = rational (r.D*r1.N+r.N*r1.D,r1.D*r.D)

let normalize (p : polynomial) : normalized_polynomial = 
    let maxDegree = (polynomial_degree p)
    let arr=Array.create (maxDegree+1) 0Q
    let (Polynomial poli) = p
    poli |>List.iter ( fun (Monomial(q,n))->
                            let gradi = monomial_degree (Monomial (q,n))
                            in arr.[gradi]<-sommaFrazioni (arr.[gradi]) q )
    NormalizedPolynomial arr

let rec derive (p : polynomial) : polynomial =
    let (Polynomial poli) = p 
    in Polynomial (poli|>List.map(fun (Monomial (q,n))->
        if n = 0 then (Monomial (0Q,0))
        else (Monomial (rational((q.N)*n,q.D),n-1))))

let reduce (e : expr) : polynomial =
    let rec countNumDerive n ex =
        match ex with
        |Poly p -> (p,n)
        |Derive d -> countNumDerive (n+1) d
    let (p,count) = countNumDerive 0 e
    in match count with
          0-> p
         |_->
            let mutable (Polynomial pol) = p
            for i = 0 to count-1 do 
                let (Polynomial poli) = derive (Polynomial pol)
                pol <- poli
            Polynomial pol

let solve0 (np : normalized_polynomial) : bool = 
    let (NormalizedPolynomial l) = np
    if l.[0] = 0Q then true else false

let solve1 (np : normalized_polynomial) : rational = 
    let (NormalizedPolynomial l) = np
    let termine_noto =  l.[0]
    in if l.[0] = 0Q then 0Q
        else
            let coef = l.[1]
            rational (termine_noto.N*coef.D,termine_noto.D*(-1)*coef.N)
            
let solve2 (np : normalized_polynomial) : (float * float option) option =
    let (NormalizedPolynomial l) = np
    let a = l.[2]
    let b = l.[1]
    let c = l.[0]
    let delta = (rational.Pow (b,2)) - 4Q*a*c
    match delta with
     q when q=0Q-> Some (((float b.N)*(-1.0)/float b.D)/2.0,None) 
    |q when q<0Q -> failwith "L'equazione non ha soluzioni reali"
    |_-> 
        let first_sol = (((float b.N)*(-1.0)/float b.D) + (rational.Sqrt delta))/2.0
        let second_sol = (((float b.N)*(-1.0)/float b.D) - (rational.Sqrt delta))/2.0
        in Some (first_sol,Some second_sol)

let solve3 (np : normalized_polynomial) : (String * String * String)  =
    let (NormalizedPolynomial l) = np
    let a = float (l.[2].N)/float (l.[2].D)
    let b = float (l.[1].N)/float (l.[1].D)
    let c= float (l.[0].N)/float (l.[0].D)
    let p = ((a**2.0)*(-1.0))/3.0 + b
    let q = 2.0*(a**3.0)/27.0 - (a*b)/3.0 + c
    let delta = (q**2.0)/4.0 + (p**3.0)/27.0
    match delta with
    0.0-> 
        let y1 = string (-2.0*((q/2.0)**(1.0/3.0)))
        let y2 = string ((q/2.0)**(1.0/3.0))
        in (" y1 = "+y1," y2 = "+y2," y3 = "+y2)
    |n when n > 0.0 ->
        let u = (-q/2.0 + delta**(1.0/2.0))**(1.0/3.0)
        let v = (-q/2.0 - delta**(1.0/2.0))**(1.0/3.0)
        let y1 = string (u + v) 
        let y2 = (string u) + "( -1/2 + i 3.0**(1.0/3.0) )" + (string v) + "( -1/2 - i 3.0**(1.0/3.0) )"
        let y3 = (string u) + "( -1/2 - i 3.0**(1.0/3.0) )" + (string v) + "( -1/2 + i 3.0**(1.0/3.0) )"
        in (" y1 = "+y1," y2 = "+y2," y3 = "+y3)
    |_->
        let y1 = string (2.0*((-p/3.0)**(1.0/2.0))) + " cos T/3"
        let y2 = string (2.0*((-p/3.0)**(1.0/2.0))) + " cos (T+2pi)/3"
        let y3 = string (2.0*((-p/3.0)**(1.0/2.0))) + " cos (T+4pi)/3"
        in (" y1 = "+y1," y2 = "+y2," y3 = "+y3)

let polinomioEquazione ((p1:polynomial),(p2:polynomial)) : polynomial =
    let (Polynomial pol1 ) = p1
    let (Polynomial pol2_negato ) = polynomial_negate p2
    in  (Polynomial(pol1@pol2_negato))






