'use client'

import React from 'react';
import { usePathname } from 'next/navigation';
import { SideVars } from './sideVars';

export const SideBar = () => {
  const pathname = usePathname();

  return (
    <div>
      <h1>Current Pathname: {pathname}</h1>
      {SideVars[pathname] ? (
        SideVars[pathname].map((value: string, index: number) => (
          <div key={index}>
            {value}
          </div>
        ))
      ) : (
        <div>No items to display</div>
      )}
    </div>
  );
};
