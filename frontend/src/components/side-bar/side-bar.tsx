'use client'

import React from 'react';
import { usePathname } from 'next/navigation';
import { SideVars } from './sideVars';
import { checkIsTokenExpired, getTokens, getUserRole, isTokenInLS } from '@/utils/token';
import { Button } from '../ui/button';
import Link from 'next/link';

export const SideBar = () => {
  const pathname: string = usePathname();

  const isRouteActive = (pathname: string, url: string) => {
    return pathname.includes(url);
  };

  // Проверка наличия и валидности токена
  const token = getTokens().access_token;
  const isAuthenticated = isTokenInLS() && token && !checkIsTokenExpired(token);
  

  if (!isAuthenticated) {
    return null;
  }

  const userRole = getUserRole() as string;

  return (
    <div className="w-[5s-rem] flex flex-col gap-8 text-lg mr-5 bg-white">
      {SideVars[userRole]?.[pathname] ? (
        SideVars[userRole][pathname].map((value, index) => (
          <Link href={value.link} key={index}>
            <Button
              className="bg-color bg-purple-600 hover:bg-green-600"
              variant={isRouteActive(pathname, value.link) ? 'ghost' : 'outline'}
              key={index}
            >
              {value.text}
            </Button>
          </Link>
        ))
      ) : (
        <div>Пунктов меню для данного пути и роли нет.</div>
      )}
    </div>
  );
};