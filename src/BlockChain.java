import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 1;

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // Looping through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // Comparing registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            // Comparing previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            // Checking if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {

        // Adding blocks to the blockchain ArrayList
        blockchain.add(new Block("First block", "0"));
        System.out.println("Attempting to mine block 1 ...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Attempting to mine block 2 ...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Attempting to mind block 3 ...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("Blockchain is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Blockchain: ");
        System.out.println(blockchainJson);

    }
}
