package Visitor.serching2 ;

import java.util.Map;

public interface BTVisitor<K,V> {
	//null���������Ēl��Ԃ����߂̃��\�b�h
    public abstract Object visitNull( );

	//����m�[�h�ɂ���f�[�^data�C���̃m�[�h�̍��̕����؂�������������leftValue�C
    //����т��̃m�[�h�̉E�̕����؂�������������rightValue����C���̃m�[�h��
    //�����������ʂ�Ԃ����߂̃��\�b�h
    public abstract Object visitNode(Object leftValue, Object rightValue, Map.Entry<K,V> data) ;
}
