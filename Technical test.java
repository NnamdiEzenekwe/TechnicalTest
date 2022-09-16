public class Test {

    public static void main(String[] args) {
        int[] A = { 5,4,5,4,5,4,4,5,3,3,3,2,2,1,5};
        System.out.println(findNthRarest(A, 4));
    }

    private static int findNthRarest(int[] A, int n) {
        Map<Integer, Long> sortedMap =
                Arrays.stream(A).boxed()
                      .collect(groupingBy(Function.identity(), counting())).entrySet().stream()
                      .sorted(Entry.comparingByValue())
                      .collect(toMap(Entry::getKey, Entry::getValue, (k, v) -> k, LinkedHashMap::new));
        List<Long> occurrences =
                sortedMap.entrySet().stream()
                         .map(Entry::getValue)
                         .distinct().collect(Collectors.toList());
        Optional<Entry<Integer, Long>> answer =
                sortedMap.entrySet().stream()
                         .filter(e -> e.getValue().intValue() == occurrences.get(n-1)).findFirst();
        return answer.get().getKey();
    }
}