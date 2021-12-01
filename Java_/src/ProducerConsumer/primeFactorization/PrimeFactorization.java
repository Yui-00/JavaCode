package ProducerConsumer.primeFactorization ;

// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public  class PrimeFactorization {
	private static final int  SIZE = 100;  // �u���b�L���O�L���[�ɒu����t�@�C���̍ő��.

	// ������S������X���b�h�̖{��.
	private static final int  SEARCHER = Runtime.getRuntime().availableProcessors() - 2 ;

	@SuppressWarnings("resource")
	public static void main(String[ ] args ) {
		//System.out.println("#availableThreads: " +
		//	Runtime.getRuntime().availableProcessors()) ;

		//�����X���b�h�̖{����\��
		System.out.println("#searchers: " + SEARCHER) ;

		// �w�肳�ꂽ�T�C�Y�̃u���b�L���O�L���[���쐬����
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>( SIZE ) ;

		// �񋓃X���b�h�̎d���𐶐�����.
		EnumerationTask et = new EnumerationTask(queue) ;

		// �t�@�C���̗񋓂�S������X���b�h��1�{�쐬����D
		Thread enumerator = new Thread( et ) ;

		// �����X���b�h���������񐶐�����D
		Thread[ ] searchers = new Thread[SEARCHER]; //�����X���b�h���o����z��𐶐�.
		for (int i = 0 ; i < SEARCHER ; i++) {
			searchers[i] = new Thread( new FactorizationTask(queue) ) ; // i�Ԗڂ̌����X���b�h���쐬����.
		}

		// �����X���b�h���N������D
		for (int i = 0 ; i < SEARCHER ; i++)
			searchers[i].start( ) ; // i�Ԗڂ̌����X���b�h���N������.

		// �t�@�C���̗񋓂�S������X���b�h���N������D
		enumerator.start( ) ;
	} // main���\�b�h�̏I���
} // KeywordSearch.java�̏I���