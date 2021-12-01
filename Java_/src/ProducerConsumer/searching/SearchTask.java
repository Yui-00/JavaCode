package ProducerConsumer.searching;

//Fileクラスを使うので必要
import java.io.File;
//BlockingQueueインターフェースを使うので必要
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
	private BlockingQueue<File> queue ; // ブロッキングキューを指す変数.
	private String keyword ; // 検索キーワード
	
	// コンストラクタ（検索の仕事を１つ作る）
	SearchTask(BlockingQueue<File> queue, String keyword){
	this.queue = queue;//ブロッキングキューへの参照を呼び出し側のプログラムからもらう.
	this.keyword = keyword; // 検索キーワードへの参照を呼び出し側のプログラムからもらう.
	}
	
	// このタスクを使ったスレッドが実行する仕事
	public void run() {
		try {
			while(true) {
				File file = queue.take() ; // キューから一戸取り出す
				
				// DAMMYなら即終了
				// 同じインスタンスを参照しているかどうか
				if(file == EnumerationTask.DUMMY_FILE) {
					return ;
				}
				
				// keywordを含むかチェック
				String line = file.getName() ; // file名を文字列として格納
				if(line.contains(keyword)) { // 含んでいたらディレクトリとfile名を表人
					System.out.println(line) ;
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt() ;
		}
	}
}
