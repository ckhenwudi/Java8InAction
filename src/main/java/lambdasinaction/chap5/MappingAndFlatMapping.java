package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : zhoujianjun
 * @version V1.0
 * @Project: Java8InAction
 * @Package lambdasinaction.chap7
 * @Description:
 * 题目：给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
 * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
 * 表数对。
 *
 * FAQ：map 与 flatmap的区别:
 * 相同点：map和flatmap处理后的数据都是Stream<R>类型
 * 不同点：map处理后数据会成为一个Stream集合即List<Stream<R>>,而flatMap处理后的对象还是一个Stream对象。
 * 参考foreach过程。
 * @date Date : 2020年04月10日 17:08
 */
public class MappingAndFlatMapping {
    public static void main(String[] args) {

        // map 与 flatmap的区别
        Arrays.asList(1, 2, 3).stream().map(n -> {
            return Arrays.asList(3, 4).stream().map(m -> {
                return new int[]{n, m};
            });
        }).forEach(pairStream -> {
            // pair-> Stream<R>
            pairStream.forEach(pair -> {
                System.out.println("(" + pair[0] + ", " + pair[1] + ")");
            });
        });

        System.out.println("---------------");

        Arrays.asList(1, 2, 3).stream().flatMap(n -> {
            return Arrays.asList(3, 4).stream().map(m -> {
                return new int[]{n, m};
            });
        }).forEach(pair -> {
            // pair->R
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        });

        //
        List<Stream<int[]>> listStream = Arrays.asList(1, 2, 3).stream().map(n -> {
            return Arrays.asList(3, 4).stream().map(m -> {
                return new int[]{n, m};
            });
        }).collect(Collectors.toList());

        List<int[]> list = Arrays.asList(1, 2, 3).stream().flatMap(n -> {
            return Arrays.asList(3, 4).stream().map(m -> {
                return new int[]{n, m};
            });
        }).collect(Collectors.toList());
    }
}