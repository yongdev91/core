package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberReposiroty;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberReposiroty();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discoint(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
