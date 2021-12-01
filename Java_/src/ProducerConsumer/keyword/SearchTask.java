package ProducerConsumer.keyword ;

// File�N���X���g���̂ŕK�v
import java.io.File;
import java.io.IOException;
// Scanner�N���X���g���̂ŕK�v
import java.util.Scanner;
// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue;
 
public class  SearchTask implements Runnable {   
    protected BlockingQueue<File> queue ;  // �u���b�L���O�L���[���w���ϐ�. 
    protected String keyword ;  // �����L�[���[�h���w���ϐ�. 
  
   // �R���X�g���N�^�i�����̎d�����P���j 
    public SearchTask(BlockingQueue<File> queue, String keyword) {  
        this.queue = queue; //�u���b�L���O�L���[�ւ̎Q�Ƃ��Ăяo�����̃v���O����������炤. 
        this.keyword = keyword; // �����L�[���[�h�ւ̎Q�Ƃ��Ăяo������ �v���O����������炤. 
    }  

    public void run( ) { // �����X���b�h�̍s���d���i�u���b�L���O�L���[����t�@�C����1���� 
                                     // ����Ă��āC���̒��Ō����L�[���[�h���܂ލs�����ׂČ�����j. 
        try { 
            while ( true ) { // ���L�������ƌJ��Ԃ��F
                File  file = queue.take( ) ; // �L���[����t�@�C�����P����Ă���. 

                if ( file == EnumerationTask.DUMMY_FILE ) { // ����Ă����̂��_�~�[�Ȃ�, 
                    queue.put( file ) ; // ���ꂪ�I���̍��}�Ȃ̂ŁC�L���[�ɖ߂��D
                    return ; // �������I�������D
               } 
               if (!file.canRead()) continue;
               Scanner in = null ; 
               try { 
                   in = new Scanner(file); // �t�@�C�����J��. 
                   int lineNumber = 0 ; // �t�@�C���̍s�̔ԍ����o���邽�߂̕ϐ�. 
                   while ( in.hasNextLine( ) ) {  // �܂��������Ă��Ȃ��s������ԁC
                       lineNumber++ ;  // �s�̔ԍ����P���₵�C
                       String line = in.nextLine( ) ; // �������Ă��Ȃ����̍s��ǂݍ��݁C
                       if (line.contains(keyword)) //���̍s�������L�[���[�h���܂ޏꍇ���ʕ\��. 
                    	   //synchronized ( System.out ) {
                    	   		System.out.println(file.getPath( ) + ":" + lineNumber + ":" + line); 
                       			//System.out.println("��̍s�ɑ����Ă��̍s��\���������Ȃ�A���b�N���K�v�B") ;
                    	   //}
                   }
               } finally {  
                   if (in != null) in.close( ) ; // �J�����t�@�C�������. 
               }
          }  // while-���̏I��� 
      } catch ( IOException e) { 
    	  e.printStackTrace() ;
      } catch ( InterruptedException e) {
    	  Thread.currentThread().interrupt();
      } 
  } // run���\�b�h�̏I��� 
} // SearchTask.java �̏I���