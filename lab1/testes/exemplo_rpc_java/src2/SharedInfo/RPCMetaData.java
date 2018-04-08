package SharedInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Estrutura para representar os dados que
 * indicarão a função remota e, ao mesmo tempo,
 * os seus valores de entrada (parâmetros).
 * Assim, espera-se evitar o envio de múltiplas
 * mensagens do client onde cada um seria um argumento.
 * De forma que, agora, o server verificará apenas se a
 * RP (Remote Procedure) com o `id` passado existe.
 * O servidor apenas lerá um objeto desta classe,
 * enquanto o processo cliente irá construí-lo.
 *
 * @author micael
 */
public class RPCMetaData implements Serializable {
    private static final long serialVersionUID = 1L;
    private short id;
    private ArrayList<Object> args;

    /**
     *
     * @param funcId - O identificador único da remote procedure.
     * @param funcParams - Os parâmeros para
     */
    public RPCMetaData(short funcId, ArrayList<Object> funcParams) {
        this.id = funcId;
        this.args = funcParams;
    }

    /**
     *
     * @param funcId - O identificador único da remote procedure.
     */
    public RPCMetaData(short funcId) {
        this(funcId, null);
    }

    /**
     * Para obter o ID da RP,
     * que será útil para o server identificar a sua procedure.
     * @return O identificador único da remote procedure.
     */
    public short getId() {
        return this.id;
    }

    /**
     * Para obter os argumentos da RP,
     * que será útil para o server passar os parâmetros
     * de entrada da sua procedure.
     * @return A lista de argumentos para a remote procedure.
     */
    public ArrayList<Object> getArgs() {
        return this.args;
    }
}
