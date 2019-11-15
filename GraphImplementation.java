import java.util.*;

public class GraphImplementation implements Graph{
	int vertex;
	int matrix[][];

	public GraphImplementation(int vertices){
		this.vertex = vertices;
		matrix = new int[vertex][vertex];
	}

	public void addEdge(int src, int tar) throws Exception{
        if (src < 0 || tar < 0 || src >= matrix[0].length || tar >= matrix[0].length) {
            throw new Exception();
        }

		matrix[src][tar] = 1;

	}

	public List<Integer> neighbors(int vertex1) throws IndexOutOfBoundsException{
		if (vertex1 >= vertex){
			throw new IndexOutOfBoundsException();
		}
		List<Integer> nList = new ArrayList<Integer>();
		for (int i = 0; i < vertex; i++){
			if (matrix[vertex1][i] != 0){
				nList.add(i);
			}
		}
		return nList;
	}

	public List<Integer> topologicalSort(){
		List<Integer> schedule = new ArrayList<Integer>();
		int[] sum = new int[matrix.length];

		for (int i = 0; i < vertex; i++){
			for (int j = 0; j < vertex; j++){
				sum[i] += matrix[j][i];
			}
		}	

		for (int i = 0; i < vertex; i++){
			int n = ifZero(sum);
			if(n == -1){
				System.out.println("Ordering this graph is impossible");
				return schedule;
			}

			schedule.add(n);
			sum[n] = -1;

			for(int j = 0; j < vertex; j++){
				sum[j] -= matrix[n][j];
			}
		}
		return schedule;
	}


	private int ifZero(int[] result){
		for (int y = 0; y < matrix.length; y++){
			if (result[y] == 0){
				return y;
			} 
		}
		return -1;
	}

} 

