class Solution {

    class Node {
        int prod;
        int[] cnt;
        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    Node[] tree;
    int[] nums;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];
        
            update(1, 0, n - 1, index, value);
            Node res = query(1, 0, n - 1, start, n - 1);
            ans[q] = res.cnt[x];
        }
        return ans;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(k);
            int x = nums[l] % k;
            tree[node].prod = x;
            tree[node].cnt[x] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    Node merge(Node a, Node b) {
        Node res = new Node(k);
        res.prod = (a.prod * b.prod) % k;

        for (int i = 0; i < k; i++) {
            res.cnt[i] += a.cnt[i];
            int rem = (a.prod * i) % k;
            res.cnt[rem] += b.cnt[i];
        }

        return res;
    }

    void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            tree[node] = new Node(k);
            int x = value % k;
            tree[node].prod = x;
            tree[node].cnt[x] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid)
            update(node * 2, l, mid, index, value);
        else
            update(node * 2 + 1, mid + 1, r, index, value);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return tree[node];
        int mid = l + (r - l) / 2;
        if (qr <= mid)
            return query(node * 2, l, mid, ql, qr);
        if (ql > mid)
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}