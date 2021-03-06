package javaBase.集合.map;

/**
 * JDK 1.8 HashMap的机制
 *
 * 1.对象创建的时候：
 * public HashMap() {
 *     this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
 * }
 * DEFAULT_LOAD_FACTOR = 0.75        默认装载因子  无参构造方法中初始化
 * DEFAULT_INITIAL_CAPACITY = 16     默认容量值
 * loadFactor = DEFAULT_LOAD_FACTOR = 0.75
 * MIN_TREEIFY_CAPACITY=8  桶内链表形成树的最小阈值(桶内链表最小长度)
 *
 * 2.第一个元素进来：resize方法中初始化
 * threshold =capacity * loadFactor= DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY = 12   resize方法中初始化
 * capacity=newCap = DEFAULT_INITIAL_CAPACITY = 16 ;
 * table =  (Node<K,V>[])new Node[newCap]; -- tab.length =16      resize方法中初始化
 *
 * 3.当某个索引所在的桶装载的元素个数为8时候，tab.length = 16 ，threshold = 12
 * (扩容机制 和 红黑数机制)
 *  tab.length      threshold                                                                  binCount >= TREEIFY_THRESHOLD - 1 && (n = tab.length) < MIN_TREEIFY_CAPACITY
 *      16              12  ->第9个元素进来  -> 链表末尾添加元素，      ->   新元素添加前本桶元素数量(8)>=8个成立,容量capacity(16)小于64成立，不形成树，进行容量2倍扩容，同时阈值2倍扩容
 *                                              桶内元素数量size变为9
 *
 *      32              24  ->第10个元素进来 -> 链表末尾添加元素，      ->   新元素添加前本桶元素数量(9)>=8个成立,容量capacity(32)小于64成立，不形成树，进行容量2倍扩容，同时阈值2倍扩容
 *                                              桶内元素数量size变为10
 *
 *      64              48  ->第11个元素进来 -> 链表末尾添加元素，      ->   新元素添加前本桶元素数量(10)>=8成立个,容量capacity(64)小于64不成立，形成红黑树
 *                                              桶内元素数量size变为11
 * ->形成红黑树后，添加元素
 * ->               新元素从叶子节点添加
 *
 * 4. 阈值的作用
 * ->  每次添加元素后， size加1，并判断 size是否大于threshold， 如果大于，就进行一次:容量2倍扩容，同时阈值2倍扩容
 *  if (++size > threshold)
 *             resize();
 *
 *
 * 5. 2倍扩容的时候
 * UNTREEIFY_THRESHOLD = 6
 *
 * 5.1  2倍扩容的时候会重新创建新表，并将旧表的元素重新计算放到新表中。
 *      oldTab[index] 中的元素会放到 newTab[index] 或者newTab[index+oldTab.length] 两个位置中。
 * 5.2  如果oldTab[index]存储的是红黑树：
 *      那么将每个TreeNode元素放到地位newTab[index]和高位newTab[index+oldTab.length] 的index位置时候，会分别记录两个位置的新链表长度：lc ，hc
 *      if(lc <= UNTREEIFY_THRESHOLD){
 *            将newTab[index]位置单链表形式存储的TreeNode节点变成 单链表形式的Node节点。(TreeNode是Node的子类)
 *            (其实就是用对应位置的TreeNode节点属性值(hash,key,value)，重新new一个Node节点，然后替换掉TreeNode；而不是向上转型形成Node节点 )
 *      }
 *
 *
 * 6. 红黑树机制：
 * 6.0 红黑树总是通过旋转和变色达到自平衡。
 * 6.1 红黑树其实即使一个 介于二叉树和严格自平衡二叉树之间的 非严格自平衡的二叉树。
 * 6.2 父节点的左子树存放的都是小于等于父节点值的子节点，同理右子树的值都是大于父节点的值。(java的HashMap的值比较是根据 key的hash值进行比较的)
 *
 * 红黑树的五大特性：
 * 6.3 a.红黑树的每个节点都会持有一种颜色:红色或者黑色 。
 * 6.4 b.红黑树的根节点root一定是黑色的。
 * 6.5 c.红黑树的每个空叶子节点(Nil或Null)一定是黑色的； 非空叶子节点一般是红色的；
 * 6.6 d.如果一个节点是红色的，那么它的子节点必须是黑色的。
 * 6.7 e.每个节点到空叶子节点所经过的黑色节点的个数必须是一样的。【确保没有一条路径会比其他路径长出两倍，所以红黑树是相对接近平衡的二叉树】
 *
 * 另外：
 * 6.8 每次给红黑树添加的新节点，都会设置为红色。然后再通过一些手段(左旋，右旋，节点变色等)来调整树的平衡性.
 * 6.9 红黑树始终处于平衡状态。 (即每个节点到空叶子节点所经过的黑色节点的个数始终是一样的。)
 *
 * 6.10 左旋 和 右旋
 * 6.10.1 左旋： 以某个节点作为支点(旋转点)，(进行逆时针旋转):
 *                 [旋转节点的右子节点]变为[旋转节点]的父节点;
 *                 [旋转节点的右子节点]的[左子节点]变为[旋转节点]的右子节点;
 *                 [旋转节点的右子节点]的[右子节点]保持不变;
 *                 [旋转节点的左子节点]保持不变;
 *
 * 6.10.2 右旋： 以某个节点作为支点(旋转点)，(进行顺时针旋转):
 *                 [旋转节点的左子节点]变为[旋转节点]的父节点;
 *                 [旋转节点的左子节点]的[右子节点]变为[旋转节点]的左子节点;
 *                 [旋转节点的左子节点]的[左子节点]保持不变;
 *                 [旋转节点的右子节点]保持不变;
 *
 *
 * 6.10.3  左旋和右旋的特点 ：
 *         左旋只影响旋转节点和其右子树的结构，即把右子树的节点往左子树挪了。
 *         右旋只影响旋转节点和其左子树的结构，即把左子树的节点往右子树挪了。
 *
 *         可以看出： 旋转操作不会影响旋转节点的父节点，父节点以上的结构还是保持不变的。
 *         所以旋转操作是局部的。
 *
 *         故：
 *         左子树的节点层数比右子树少，就可以进行左旋向右子树借节点，一定程度上来达到量左右子树节点的平衡。
 *         右子树的节点层数比左子树少，就可以进行右旋向左子树借节点，一定程度上来达到量左右子树节点的平衡。
 *
 * 6.11 探索JDK1.8 HashMap在为红黑树添加新节点时：
 *
 * 6.11.1. 根据节点的hash值来确定节点的存放位置: 如果<=父节点的hash值，放到父节点的左子节点,否则放到右子节点。
 * 6.11.2. 将新节点添加到树的叶子节点(即从尾部插入数据) -> 进行树的平衡  -> 新节点颜色设置为红色
 *    -> 开始平衡树的逻辑(左旋，右旋，节点变色等) -> 将根节点设置为黑色
 *
 *
 * 6.12 从上面可知：
 *
 * HashMap 每次扩容和添加元素，都有可能涉及到 红黑树的平衡调整 ，平衡调整是一个很耗时的操作，应尽量避免。
 * 所以在HashMap的时候，尽量减少扩容的次数。(比如可以通过 预估要添加的元素数量，如果数量超过12，就给一个大于16的初始容量值 )
 */
public class MapDesc {
}
