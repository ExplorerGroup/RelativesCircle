package com.android.relativescircle.utils;

import com.android.relativescircle.model.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by fantao on 17-2-21.
 */

public class MemberListManager {
    private static MemberListManager sMemberListManager;
    private List<Member> memberList;
    private TreeMap<Integer, Member> memberMap;
    private Map<Long, Integer> idMap;
    private int generationCount = -1;
    private int maxGeneration = -1;
    private int totalWeight;

    private MemberListManager(List<Member> ms) {
        memberList = ms;
        memberMap = new TreeMap<Integer, Member>();
        idMap = new HashMap<Long, Integer>();
        for (int i = 0; i < ms.size(); i++) {
            memberMap.put(i, ms.get(i));
            idMap.put(ms.get(i).getId(), i);
        }
        calculatorWeight();
    }

    public static synchronized MemberListManager getInstance() {
        if (sMemberListManager == null) {
            List<Member> ms = new ArrayList<Member>();
            Member m1 = new Member(13453176, "王sss", 0, 0, null, 0);
            Member m2 = new Member(96732498, "李sss", 1, 0, m1, 1);
            Member m3 = new Member(53477523, "王xxx", 0, 1, m1, 0);
            Member m4 = new Member(67356544, "张xxx", 1, 1, m3, 1);
            Member m5 = new Member(23433534, "王eee", 1, 1, m1, 2);
            Member m6 = new Member(89076856, "王ttt", 1, 1, m1, 3);
            Member m7 = new Member(21343435, "王rrr", 1, 1, m1, 4);
            Member m8 = new Member(34345435, "王uuu", 1, 1, m1, 5);
            Member m9 = new Member(75688656, "王ooo", 0, 1, m1, 6);
            Member m10 = new Member(13424347, "赵ooo", 1, 1, m9, 7);
            Member m11 = new Member(63454648, "王iii", 0, 2, m3, 0);
            Member m12 = new Member(45346424, "周iii", 1, 2, m11, 1);
            Member m13 = new Member(98678445, "王ggg", 0, 2, m3, 2);
            Member m14 = new Member(14332453, "谢ggg", 1, 2, m13, 3);
            Member m15 = new Member(23456543, "王kkk", 0, 2, m3, 4);
            Member m16 = new Member(21343543, "张kkk", 1, 2, m15, 5);
            Member m17 = new Member(23456654, "王ppp", 0, 2, m9, 6);
            Member m18 = new Member(17545646, "王www", 1, 3, m11, 0);
            Member m19 = new Member(27685675, "王mmm", 1, 3, m13, 1);
            Member m20 = new Member(95346542, "王zzz", 0, 3, m15, 2);
            Member m21 = new Member(87434564, "王ccc", 1, 3, m15, 3);
            Member m22 = new Member(54361434, "刘ppp", 1, 2, m17, 7);
            ms.add(m1);
            ms.add(m2);
            ms.add(m4);
            ms.add(m6);
            ms.add(m3);
            ms.add(m7);
            ms.add(m5);
            ms.add(m8);
            ms.add(m9);
            ms.add(m10);
            ms.add(m11);
            ms.add(m12);
            ms.add(m14);
            ms.add(m15);
            ms.add(m13);
            ms.add(m16);
            ms.add(m19);
            ms.add(m18);
            ms.add(m17);
            ms.add(m20);
            ms.add(m21);
            ms.add(m22);
            Collections.sort(ms);
            sMemberListManager = new MemberListManager(ms);
        }
        return sMemberListManager;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public int getGenerationCount() {
        if (generationCount == -1) {
            generationCount = memberList.get(memberList.size() - 1).getGeneration() - memberList.get(0).getGeneration() + 1;
        }
        return generationCount;
    }

    public Member findMemberById(long id) {
        for (Member member : memberList) {
            if (id == member.getId()) {
                return member;
            }
        }
        return null;
    }

    private int calculatorWeight(Member member) {
        if (member.isWife()) {
            return 0;
        }
        int count = 0;
        Iterator it = memberMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry =(Map.Entry) it.next();
            Member m = (Member) entry.getValue();
            if (m.getGeneration() - member.getGeneration() == 1 && m.getRelativeMember().equals(member)) {
                count += m.getWeight();
            }
        }
        return count < 2 ? count + 1 : count;
    }

    private void calculatorWeight() {
        int maxG = memberList.get(memberList.size() - 1).getGeneration();
        for (int i = memberList.size() - 1; i > -1; i--) {
            Member m = memberList.get(i);
            int w = calculatorWeight(m);
            if (w == 1 && (!hasBandS(i, m) || (hasBandS(i, m) && hasWife(i, m)))) {
                w = 2;
            }
            m.setWeight(w);
            memberMap.put(i, m);
        }
        Iterator it = memberMap.entrySet().iterator();
        int t = 0; long f = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Member m = (Member) entry.getValue();
            Member rm = getNewRelativeMember(m);
            if (m.getGeneration() == 0) {
                m.setPosition(0);
            } else {
                if (m.isWife()) {
                    m.setPosition(rm.getPosition());
                } else {
                    if (f != rm.getId()) {
                        t = 0;
                        f = rm.getId();
                    }
                    m.setPosition(rm.getPosition() + t);
                    t += m.getWeight();
                }
            }
            memberMap.put((int) entry.getKey(), m);
        }
        memberList.clear();
        Iterator im = memberMap.entrySet().iterator();
        while (im.hasNext()) {
            Map.Entry entry = (Map.Entry) im.next();
            Member m = (Member) entry.getValue();
            memberList.add(m);
        }
        totalWeight = memberList.get(0).getWeight();
    }

    private boolean hasWife(int k, Member m) {
        return k + 1 < memberList.size() && memberList.get(k + 1).getRelativeMember().equals(m);
    }

    private boolean hasBandS(int k, Member m) {
        int count = 0;
        if (k > 0) {
            Member b = memberList.get(k - 1);
            if (b.getRelativeMember().equals(m.getRelativeMember())
                    || (b.getRelativeMember().getRelativeMember() != null
                    && b.getRelativeMember().getRelativeMember().equals(m.getRelativeMember()))) {
                count++;
            }
        }
        if (k + 1 < memberList.size() && memberList.get(k + 1).getRelativeMember().equals(m.getRelativeMember())) {
            count++;
        }
        return count > 0;
    }

    public int getRelativeMemberIndex(Member m) {
        if (!idMap.containsKey(m.getRelativeId())) {
            return -1;
        } else {
            return idMap.get(m.getRelativeId());
        }
    }

    public Member getNewRelativeMember(Member m) {
        int index = getRelativeMemberIndex(m);
        if (index > -1 && index < memberList.size()) {
            return memberList.get(index);
        }
        return null;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    class MyCompartor implements Comparator {

        @Override
        public int compare(Object lhs, Object rhs) {
            Member ml = (Member) lhs;
            Member mr = (Member) rhs;
            Member m1 = ml.getGeneration() == ml.getRelativeMember().getGeneration() ? ml.getRelativeMember() : ml;
            Member m2 = mr.getGeneration() == mr.getRelativeMember().getGeneration() ? mr.getRelativeMember() : mr;
            int s = 0;
            if (m1.getRelativeMember().getSortCode() - m2.getRelativeMember().getSortCode() > 0) {
                s = 1;
            } else if (m1.getRelativeMember().getSortCode() - m2.getRelativeMember().getSortCode() < 0) {
                s = -1;
            }
            int d = 0;
            if (s == 0) {
                if (m1.getDateOfBirth() != null && m2.getDateOfBirth() != null) {
                    d = m1.compareTo(m2);
                }
            } else {
                return s;
            }
            int n = 0;
            if (d == 0) {
                n = m1.getName().compareTo(m2.getName());
            } else {
                return d;
            }
            if (n == 0) {
                return ml.getSex() - mr.getSex();
            } else {
                return n;
            }
        }
    }
}
