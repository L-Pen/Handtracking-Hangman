import java.io.*;
import java.util.*;

public class A1_Q3 {
	
	public static ArrayList<String> strToAL(String[] input){
		List<String> transitionElement = new ArrayList<String>();
		transitionElement = Arrays.asList(input);
		ArrayList<String> finalElement = new ArrayList<>(transitionElement);
		return finalElement;
	}

	public static ArrayList<String> sort(ArrayList<Integer> intArray, ArrayList<String> wordArray ){
		// System.out.println(wordArray);
		// System.out.println(intArray);


		for(int i = 0; i < wordArray.size(); i++){
			// System.out.println("i: " + wordArray.get(i));
			for(int j = i; j < wordArray.size(); j++){
				if(intArray.get(i) < intArray.get(j)){
					// System.out.println("hit simple case" + wordArray.get(i) + " "+ wordArray.get(j));
					Collections.swap(intArray,j,i);
					Collections.swap(wordArray,j,i);

				}
				else if(intArray.get(i).equals(intArray.get(j))){
					if(wordArray.get(i).compareTo(wordArray.get(j)) >=0){

						//System.out.println("hit apha case" + wordArray.get(i) + " " + wordArray.get(j));
						Collections.swap(intArray,j,i);
						Collections.swap(wordArray,j,i);
					}
				}
			}
		}
		
		return wordArray;
	}
		

	public static ArrayList<String> Discussion_Board(String[] posts){

		ArrayList<String> result = new ArrayList<String>();

		HashMap<String,ArrayList<String>> nameHashmap = new HashMap<String,ArrayList<String>>();
		HashMap<String,Integer> wordCountHashmap = new HashMap<String,Integer>();

		for(String element: posts){
			String[] splitElement = element.split(" ");

			String[] noName = Arrays.copyOfRange(splitElement, 1, splitElement.length);
			ArrayList<String> al = strToAL(noName);

			String name = splitElement[0];

			if(nameHashmap.containsKey(name)){
				ArrayList<String> hashmapList = nameHashmap.get(name);
				for (String newWord: al){
					hashmapList.add(newWord);
				}
				nameHashmap.put(name,hashmapList);
			}else{
				nameHashmap.put(name,al);
			}

			for(String word: noName){ 
				if(wordCountHashmap.containsKey(word)){
					//insert word in wordCountHashmap
					wordCountHashmap.put(word,wordCountHashmap.get(word) + 1); //increment if it exists
				} else{
					wordCountHashmap.put(word,1); //assign 1 to word
				}
			}
		}


		// for(String word:wordCountHashmap.keySet()){
		// 	boolean found = false;
		// 	if(wordCountHashmap.get(word) >= nameHashmap.keySet().size()){

		// 		for(String name: nameHashmap.keySet()){
		// 			if(nameHashmap.get(name).contains(word)){
		// 				found = true;
		// 			}
		// 			else{
		// 				found = false;
		// 			}
		// 		}

		// 		if(found){
		// 			result.add(word);
		// 		}
		// 	}
		// 	else{
		// 		continue;
		// 	}
		// }
        for(String word: wordCountHashmap.keySet()){
            int i = 0;
            for(String name: nameHashmap.keySet()){
                if(nameHashmap.get(name).contains(word)){
                    i++;
                }
            }
            if(i == (nameHashmap.size())){
                result.add(word);
            }
        }
		// should be receiving empty list because not every word is used by every person, very long, 50 messages
		ArrayList<Integer> wordCountList = new ArrayList<Integer>();
		for(String word: result){
			wordCountList.add(wordCountHashmap.get(word));
		}

		result = sort(wordCountList, result);
		
		return result;
	}

	public static void main(String[] args) {
		String[] posts = {"David  ali ali no no no no nabody never ali not dad", "Jennifer why ever not nabody not not ali dad", "Parham no not never ali nabody dad", "Shishir no ali not dad never know nabody", "Alvin why no not ali nabody dad ", "Alvin nabody never ali ali know why nabody dad", "David never no nabody", "Jennifer never never  nabody no"};
		// String[] posts = String[10000];
        // String test = "h ah a a a oso a hi my name is aj l a";
        // for (int i = 0; i < 10000; i ++){
            
        // }
        
        System.out.println(Discussion_Board(posts));

	}

}
