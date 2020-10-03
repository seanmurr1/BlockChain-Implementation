import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {




    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {

        // Adding blocks to the blockchain ArrayList
        blockchain.add(new Block("First block", "0"));
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);

    }
}
