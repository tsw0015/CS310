use std::io;

fn main(){
	let mut line = String::new();
	println!("Input data");
	let input = io::stdin().read_line(&mut line).unwrap();
	println!("What I saw: {}",input);	


}
