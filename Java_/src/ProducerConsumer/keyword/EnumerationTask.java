package ProducerConsumer.keyword ;

// File�N���X���g���̂ŕK�v
import java.io.File;
// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue;
 
public class  EnumerationTask implements Runnable {  
	protected final static File  DUMMY_FILE = new File("") ; //�񋓂̏I���������̂Ɏg����. 
	protected BlockingQueue<File> queue ;  // �u���b�L���O�L���[���w���ϐ�. 
	protected File startDirectory ;  // �����̊J�n�f�B���N�g�����w���ϐ�. 

	// �R���X�g���N�^�i�񋓂̎d�����P���j 
	public EnumerationTask(BlockingQueue<File> queue, File startDirectory) {  
		this.queue = queue ; // �u���b�L���O�L���[���Ăяo�����̃v���O����������炤. 
		this.startDirectory = startDirectory ; // �����̊J�n�f�B���N�g�����Ăяo����������炤. 
	}  

	// �����X���b�h�̍s���d���i�w�肳�ꂽ�f�B���N�g���Ƃ��̃T�u�f�B���N�g���̉��ɂ���t�@�C���̗񋓁j 
	public void run( ) {  
		try { 
			enumerate(startDirectory); //���상�\�b�h�i���Łj�Ńt�@�C����񋓂��ăL���[�ɒǉ�. 
			queue.put(DUMMY_FILE); // �L���[�Ƀ_�~�[��u���ii.e., �񋓂̊����̍��}�𑗂�j. 
		} catch ( InterruptedException e) {
			Thread.currentThread().interrupt();
		} 
	} 

	// ����directory�̎����f�B���N�g���̉��ɂ���t�@�C���i�T�u�f�B���N�g�����܂ށj�� 
	// ���ׂė񋓂��ău���b�L���O�L���[�ɒu�����߂̃��\�b�h. 
	public void enumerate( File directory ) throws InterruptedException { 
		File[ ] files = directory.listFiles( ) ; // ����directory �̎����f�B���N�g���̂����� 
	                                       // �ɂ��邷�ׂẴt�@�C���i�f�B���N�g�����܂ށj��Ԃ�. 
		if (files == null)   // ����directory �͐������p�X���ł͂Ȃ���΁C
			return ;            // �������Ȃ��ŗ񋓂��I����D 
		for (int i = 0; i < files.length ; i++) {  
			if ( files[i].isDirectory( ) ) // �f�B���N�g���ł���΁C���̉�����т��̃T�u 
				enumerate( files[i] ) ; // �f�B���N�g���̉��ɂ���t�@�C�����ċA�I�ɗ�. 
			else                   // �f�B���N�g���ł͂Ȃ���΁ii.e.�C���ʂ̃t�@�C���ł���΁j�C
				queue.put( files[i] ) ;  // ������u���b�L���O�L���[�ɒǉ�����D
		}
	} // enumerate���\�b�h�̏I���
} // EnumerationTask.java �̏I���