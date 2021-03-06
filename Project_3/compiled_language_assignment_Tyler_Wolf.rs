/*
Unit 3  Compiled Language Portion of Assignment for Tyler Wolf
    I acknowledge and agree to the acedemic integrity policy.
*/

use std::io;

fn main() {

   let mut cuts = String::new();
   let mut dimensions = String::new();
   println!("What is the number of cuts you want to use?");
   io::stdin().read_line(&mut cuts).unwrap();
   println!("What is the number of dimensions you want to use?");
   io::stdin().read_line(&mut dimensions).unwrap();
   let cutsTrim = cuts.trim();
   let dimensionsTrim = dimensions.trim();
   let res = hypercake(cutsTrim.parse().unwrap(), dimensionsTrim.parse().unwrap());
   println!("The answer to the hypercake problem with {} cuts and {} dimensions is {}.C",cutsTrim,dimensionsTrim,res);
}




fn hypercake(n: i32, k: i32) -> i32 {

    fn combinations(n: i32, r: i32) -> i32 {

        fn factorial(num: i32) -> i32 {
            if num == 0 {
                return 1;
            }
            else {
                return num * factorial(num-1);
            }
        }

        if r >= 0 && r <= n {
            return factorial(n)/(factorial(r)*factorial(n-r));
        }
        else{
            return 0;
        }
    }

    if k == 0{
        return combinations(n,k);
    }
    else {
        return combinations(n,k) + hypercake(n,k-1);
    }
}