package com.baskbull.library_system.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.baskbull.library_system.common.lang.constant.ReaderConsts;
import com.baskbull.library_system.entity.Reader;
import com.baskbull.library_system.service.ReaderService;
import com.baskbull.library_system.shiro.vo.AccountProfile;
import com.baskbull.library_system.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm
 * @author baskbull
 */
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ReaderService readerService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken)authenticationToken;
        //Id放在subject里面
        String readerId = jwtUtil.getClaimByToken((String) token.getPrincipal()).getSubject();

        Reader reader = readerService.getById(readerId);
        if(reader == null){
            throw new UnknownAccountException("账户不存在");
        }

        if(reader.getRdStatus().equals(ReaderConsts.READER_LOSS) && reader.getRdStatus().equals(ReaderConsts.READER_DISABLE)){
            throw new LockedAccountException("账户已被锁定");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtil.copyProperties(reader,accountProfile);
        /**
         * SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
         */
        return new SimpleAuthenticationInfo(accountProfile,token.getCredentials(),getName());

    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
