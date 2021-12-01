package ProducerConsumer.searching;

// Fileクラスを使うので必要
import java.io.File;
// BlockingQueueインターフェースを使うので必要
import java.util.concurrent.BlockingQueue;

// ファイルを列挙するタスク
public class EnumerationTask implements Runnable {
	final static File DUMMY_FILE = new File(""); //列挙の終了を示すのに使う
	private File startDirectory ; //開始ディレクトリ
	private BlockingQueue<File> queue ; // ブロッキングキューを指す変数.
	
	// コンストラクタでqueueの参照とディレクトリの参照をもらう
	EnumerationTask(File staratDirectory, BlockingQueue<File> queue){
		this.startDirectory = staratDirectory ;
		this.queue = queue ;
	}
	
	// このタスクを使ったスレッドが実行する仕事
	public void run() {
		try {
			enumerate(startDirectory) ;
			// キューにダミーを置く（i.e., 列挙の完了の合図を送る）.
			queue.put(DUMMY_FILE); 
		} catch (InterruptedException e){
			Thread.currentThread().interrupt() ; 
		}
	}
	
	// file列挙メソッド
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles() ;
		
		// directoryが間違いならば終了
		if(files == null) return ;
		
		// 再帰的にファイルを列挙
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				enumerate(files[i]) ;
			} else {
				queue.put(files[i]);
			}
		}
	}
}
