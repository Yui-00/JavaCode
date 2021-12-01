package ProducerConsumer.searching;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class KeywordSearch {
	private static final int SIZE = 10 ; // ブロッキングキューに置けるファイルの最大個数. 
	
	// mainメソッド
	public static void main(String[] args) {
		File startDirectory = new File("D:\\music") ; // 開始位置のFileインスタンス作成
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(SIZE) ; // キューインスタンス作成
		
		// 列挙スレッドの仕事を生成する.
		EnumerationTask et = new EnumerationTask(startDirectory, queue) ;
		// ファイルの列挙を担当するスレッドを1本作成する．
		Thread enumerator = new Thread(et) ;
		
		//けんさくすれっど
		SearchTask st = new SearchTask(queue, "君") ;
		Thread searcher = new Thread(st) ;
		
		enumerator.start() ;
		searcher.start() ;
	}
}
