#include <iostream>

class Node {
public:
    Node(int const val) {
        _val = val;
        _prevNext = nullptr;
    }

    void setPrevNext(void* prevNext) { _prevNext = prevNext; }
    void* getPrevNext() { return _prevNext; }
    int getVal() { return _val; }

private:
    void* _prevNext;
    int _val;
};

class DoubleLinkedListWithOnePointer {
public:
    DoubleLinkedListWithOnePointer() {
        _head = new Node(-1);
        _tail = new Node(-1);
    }

    void insertVal(int const val) {
        Node* cur = new Node(val);
        if (_head->getPrevNext() == nullptr) { // first element to insert
            _head->setPrevNext(cur);
            _tail->setPrevNext(cur);
            cur->setPrevNext(calculateAddress(_head, _tail));
        } else {
            Node* nextNode = (Node *)_head->getPrevNext();
            _head->setPrevNext(cur);
            cur->setPrevNext(calculateAddress(_head, nextNode));
            void* preVal = nextNode->getPrevNext();
            nextNode->setPrevNext(calculateAddress(_head, calculateAddress(preVal, cur)));
        }
    }

    bool exists(int const val) {
        Node* pre = _head;
        Node* cur = (Node *)_head->getPrevNext();
        while (cur != nullptr) {
            if (cur->getVal() == val) { return true; }
            Node* nxt = static_cast<Node *>(calculateAddress(pre, cur->getPrevNext()));
            pre = cur;
            cur = nxt;
        }
        return false;
    }

    void deleteVal(int const val) {
        Node* pre = _head;
        Node* cur = (Node *)_head->getPrevNext();
        while (cur != nullptr && cur->getVal() != -1) {
            if (cur->getVal() == val) {
                Node* nxt = static_cast<Node *>(calculateAddress(pre, cur->getPrevNext()));
                if (pre == _head && nxt == _tail) { // only node here
                    _head->setPrevNext(nullptr);
                    _tail->setPrevNext(nullptr);
                } else if (pre == _head) {
                    _head->setPrevNext(nxt);
                    nxt->setPrevNext(calculateAddress(cur, calculateAddress(nxt->getPrevNext(), _head)));
                } else if (nxt == _tail) {
                    _tail->setPrevNext(pre);
                    pre->setPrevNext(calculateAddress(cur, calculateAddress(pre->getPrevNext(), _tail)));
                } else {
                    pre->setPrevNext(calculateAddress(cur, calculateAddress(pre->getPrevNext(), nxt)));
                    nxt->setPrevNext(calculateAddress(cur, calculateAddress(nxt->getPrevNext(), pre)));
                }
                cur->setPrevNext(nullptr);
                return;
            }
            Node * nxt = static_cast<Node *>(calculateAddress(pre, cur->getPrevNext()));
            pre = cur;
            cur = nxt;
        }
        return;
    }

    void prettyPrint() {
        Node* pre = _head;
        Node* cur = (Node *)pre->getPrevNext();
        while (cur != nullptr && cur->getVal() != -1) {
            std::cout << cur->getVal() << " --> ";
            Node* nxt = static_cast<Node *>(calculateAddress(pre, cur->getPrevNext()));
            pre = cur;
            cur = nxt;
        }
        std::cout << "NULL" << std::endl;
    }

    void prettyPrintReverse() {
        Node* pre = _tail;
        Node* cur = (Node *)pre->getPrevNext();
        std::cout << "NULL";
        while (cur != nullptr && cur->getVal() != -1) {
            std::cout << " <-- " << cur->getVal();
            Node* nxt = static_cast<Node *>(calculateAddress(pre, cur->getPrevNext()));
            pre = cur;
            cur = nxt;
        }
        std::cout << std::endl;
    }

private:
    Node* _head;
    Node* _tail;

    void* calculateAddress(void* a, void* b) {
        return reinterpret_cast<void *>(reinterpret_cast<long long>(a) ^ reinterpret_cast<long long>(b));
    }
};

int main() {
    DoubleLinkedListWithOnePointer* list = new DoubleLinkedListWithOnePointer();

    std::cout << std::endl << "Testing insertVal" << std::endl;
    for (int i = 0; i < 10; ++i) {
        list->insertVal(i);
        list->prettyPrint();
        list->prettyPrintReverse();
    }

    std::cout << std::endl << "Testing exists" << std::endl;
    for (int i = 0; i < 20; i += 3) {
        std::cout << "testing " << i << ", " << list->exists(i) << std::endl;
    }

    std::cout << std::endl << "Testing deletion" << std::endl;
    for (int i = 0; i < 10; ++i) {
        list->deleteVal(i);
        list->prettyPrint();
        list->prettyPrintReverse();
    }

    return 0;
}