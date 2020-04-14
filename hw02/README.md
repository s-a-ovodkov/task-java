Задание #2 
----------
##### DIY ArrayList
###### Описание задания:
> Написать свою реализацию ```ArrayList``` на основе массива.
> ```class DIYArrayList<T> implements List<T>{...}```
###### Требования:
* Проверить, что на ней работают методы из java.util.Collections:
```
Collections.addAll(Collection<? super T> c, T... elements)
Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
Collections.static <T> void sort(List<T> list, Comparator<? super T> c)
```
* Проверяйте на коллекциях с 20 и больше элементами
* DIYArrayList должен имплементировать ТОЛЬКО ОДИН интерфейс - ```List```. 
* Если метод не имплементирован, то он должен выбрасывать исключение UnsupportedOperationException.